import { confirmDialog } from "primereact/confirmdialog";
import { lessonService } from "../../../../../services/lessonService";
import { useState } from "react";

export const useLessonActions = ({ setSelectedTurma, setTurmaDetailsVisible, showToast }) => {
  const [loading, setLoading] = useState(false);
  
  const removeLesson = async (id) => {
    try {
      await lessonService.delete(id);
      showToast("success", "Sucesso", "Aula removida com sucesso");
      window.location.reload(); 
    } catch (error) {
      console.error("Erro ao remover aula:", error);
      showToast("error", "Erro", "Falha ao remover aula");
    }
  };

  const removeGroupLessons = async (group) => {
    try {
      setLoading(true);

      const deletePromises = group.originalLessons.map((lesson) =>
        lessonService.delete(lesson.id)
      );

      await Promise.all(deletePromises);

      showToast(
        "success",
        "Sucesso",
        `${group.lessonCount} aulas removidas com sucesso`
      );
      window.location.reload();
    } catch (error) {
      console.error("Erro ao remover grupo de aulas:", error);
      showToast("error", "Erro", "Falha ao remover algumas aulas");
    } finally {
      setLoading(false);
    }
  };

  const confirmDelete = (lesson) => {
    if (lesson.isGroup) {
      confirmDialog({
        message: `Deseja remover esta sequência de ${
          lesson.lessonCount
        } aulas de ${lesson.disciplina?.nome || "disciplina"}?`,
        header: "Confirmação",
        icon: "pi pi-exclamation-triangle",
        acceptLabel: "Sim, remover todas",
        rejectLabel: "Cancelar",
        acceptClassName: "p-button-danger",
        defaultFocus: "reject",
        accept: () => removeGroupLessons(lesson),
      });
    } else {
      confirmDialog({
        message: `Deseja remover esta aula de ${
          lesson.disciplina?.nome || "disciplina"
        }?`,
        header: "Confirmação",
        icon: "pi pi-info-circle",
        acceptLabel: "Sim, remover",
        rejectLabel: "Cancelar",
        acceptClassName: "p-button-danger",
        defaultFocus: "reject",
        accept: () => removeLesson(lesson.id),
      });
    }
  };

  const showGroupDetails = (group) => {
    confirmDialog({
      message: (
        <div className="group-details">
          <h3>Sequência de Aulas: {group.disciplina?.nome}</h3>
          <p>
            <strong>Turma:</strong> {group.turma?.nome}
          </p>
          <p>
            <strong>Professor:</strong> {group.currentSchedule?.professor?.nome}
          </p>
          <p>
            <strong>Sala:</strong> {group.currentSchedule?.sala?.nome}
          </p>
          <p>
            <strong>Dia:</strong> {group.currentSchedule?.diaSemana?.nome}
          </p>
          <p>
            <strong>Horário:</strong> {group.startTime} às {group.endTime}
          </p>
          <p>
            <strong>Total:</strong> {group.lessonCount} horários consecutivos
          </p>
        </div>
      ),
      header: "Detalhes da Sequência",
      icon: "pi pi-info-circle",
      acceptLabel: "Fechar",
      acceptClassName: "p-button-primary",
      rejectStyle: { display: "none" },
    });
  };

  const openTurmaDetails = (turma) => {
    setSelectedTurma(turma);
    setTurmaDetailsVisible(true);
  };

  return {
    confirmDelete,
    showGroupDetails,
    openTurmaDetails,
    loading
  };
};