import { Button } from "primereact/button";
import { Dialog } from "primereact/dialog";
import { Dropdown } from "primereact/dropdown";
import { Toast } from "primereact/toast";
import React, { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { classScheduleService } from "../../../../services/classScheduleService";
import { classService } from "../../../../services/classService";
import { classroomService } from "../../../../services/classroomService";
import { lessonService } from "../../../../services/lessonService";
import { professorService } from "../../../../services/professorService";
import { subjectService } from "../../../../services/subjectService";
import { timeSlotService } from "../../../../services/timeSlotService";
import { weekdayService } from "../../../../services/weekdayService";
import "./styles.css";

const AddLessonPage = () => {
  const navigate = useNavigate();
  const toast = useRef(null);

  // Dados para dropdowns
  const [allSubjects, setAllSubjects] = useState([]);
  const [allClasses, setAllClasses] = useState([]);
  const [allClassrooms, setAllClassrooms] = useState([]);
  const [allWeekdays, setAllWeekdays] = useState([]);
  const [allTimeSlots, setAllTimeSlots] = useState([]);
  const [allProfessors, setAllProfessors] = useState([]);

  // Seleções do usuário
  const [selectedSubject, setSubject] = useState(null);
  const [selectedClass, setClass] = useState(null);
  const [selectedClassroom, setClassroom] = useState(null);
  const [selectedWeekday, setWeekday] = useState(null);
  const [selectedTimeSlot, setTimeSlot] = useState(null);
  const [selectedProfessor, setProfessor] = useState(null);

  const [details, setDetails] = useState(false);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      setLoading(true);
      // Carregar todos os dados necessários em paralelo
      const [
        subjectsData,
        classesData,
        classroomsData,
        weekdaysData,
        timeSlotsData,
        professorsData,
      ] = await Promise.all([
        subjectService.getAll(),
        classService.getAll(),
        classroomService.getAll(),
        weekdayService.getAll(),
        timeSlotService.getAll(),
        professorService.getAll(),
      ]);

      setAllSubjects(subjectsData || []);
      setAllClasses(classesData || []);
      setAllClassrooms(classroomsData || []);
      setAllWeekdays(weekdaysData || []);
      setAllTimeSlots(timeSlotsData || []);
      setAllProfessors(professorsData || []);
    } catch (error) {
      console.error("Erro ao carregar dados:", error);
      showToast("error", "Erro", "Falha ao carregar dados iniciais");
    } finally {
      setLoading(false);
    }
  };

  // Quando a disciplina é selecionada, filtra os professores que lecionam essa disciplina
  useEffect(() => {
    if (selectedSubject && selectedSubject.professores) {
      const professorIds = selectedSubject.professores.map(
        (p) => p.matricula || p.id
      );
      const filteredProfessors = allProfessors.filter((p) =>
        professorIds.includes(p.matricula || p.id)
      );

      if (filteredProfessors.length === 1) {
        setProfessor(filteredProfessors[0]);
      } else {
        setProfessor(null);
      }
    } else {
      setProfessor(null);
    }
  }, [selectedSubject, allProfessors]);

  const showToast = (severity, summary, message) => {
    toast.current?.show({ severity, summary, detail: message, life: 3000 });
  };

  const validData = () => {
    if (!selectedSubject) {
      showToast("warn", "Formulário Inválido", "Selecione uma disciplina");
      return false;
    }

    if (!selectedClass) {
      showToast("warn", "Formulário Inválido", "Selecione uma turma");
      return false;
    }

    if (!selectedClassroom) {
      showToast("warn", "Formulário Inválido", "Selecione uma sala");
      return false;
    }

    if (!selectedWeekday) {
      showToast("warn", "Formulário Inválido", "Selecione um dia da semana");
      return false;
    }

    if (!selectedTimeSlot) {
      showToast("warn", "Formulário Inválido", "Selecione um horário");
      return false;
    }

    console.log("Professor selecionado:", selectedProfessor);

    if (!selectedProfessor) {
      showToast("warning", "Atenção", "Selecione um professor para continuar");
      setLoading(false);
      return;
    }

    return true;
  };

  // No método registerLesson, alterar a construção do objeto scheduleData

  const registerLesson = async () => {
    if (!validData()) return;

    try {
      setLoading(true);

      // Primeiro, criar a aula (associação disciplina-turma)
      const aulaData = {
        disciplinaId: selectedSubject.id,
        turmaId: selectedClass.id,
      };

      console.log("Criando aula:", aulaData);
      const aulaResponse = await lessonService.create(aulaData);

      // Em seguida, criar a associação turma-sala com os detalhes completos
      // Aqui está a correção: usando professorMatricula ao invés de professorId
      const scheduleData = {
        turmaId: selectedClass.id,
        salaId: selectedClassroom.id,
        diaSemanaId: selectedWeekday.id,
        horarioId: selectedTimeSlot.id,
        professorMatricula: selectedProfessor
          ? selectedProfessor.matricula || selectedProfessor.id
          : null,
      };

      console.log("Criando programação:", scheduleData);
      await classScheduleService.create(scheduleData);

      showToast("success", "Sucesso", "Aula cadastrada com sucesso!");

      // Redirecionar para listagem após sucesso
      setTimeout(() => {
        navigate("/ifba/list-lesson");
      }, 2000);
    } catch (error) {
      console.error("Erro ao cadastrar aula:", error);

      // Verificar se é erro de duplicidade
      if (
        error.response &&
        error.response.data &&
        typeof error.response.data === "string" &&
        error.response.data.includes("existe")
      ) {
        showToast(
          "error",
          "Erro",
          error.response.data || "Esta aula já existe no sistema"
        );
      } else {
        showToast("error", "Erro", "Falha ao cadastrar aula");
      }
    } finally {
      setLoading(false);
    }
  };
  const timeSlotTemplate = (option) => {
    return option ? `${option.inicio} - ${option.fim}` : "";
  };

  return (
    <>
      <Toast ref={toast} />
      <section id="lesson-add-section">
        <div className="title-div">
          <h1 className="page-title main-page-title">Aulas</h1>
          <h2 className="page-title sub-page-title">Cadastrar</h2>
        </div>

        <div id="lesson-form-div">
          <div className="form-row">
            <div className="input-div input-dropdown">
              <label>Disciplina</label>
              <Dropdown
                value={selectedSubject}
                onChange={(e) => setSubject(e.value)}
                options={allSubjects}
                optionLabel="nome"
                placeholder="Selecione uma disciplina"
                className="w-full"
              />
            </div>

            <div className="input-div input-dropdown">
              <label>Turma</label>
              <Dropdown
                value={selectedClass}
                onChange={(e) => setClass(e.value)}
                options={allClasses}
                optionLabel="nome"
                placeholder="Selecione uma turma"
                className="w-full"
              />
            </div>
          </div>

          <div className="form-row">
            <div className="input-div input-dropdown">
              <label>Sala</label>
              <Dropdown
                value={selectedClassroom}
                onChange={(e) => setClassroom(e.value)}
                options={allClassrooms}
                optionLabel="nome"
                placeholder="Selecione uma sala"
                className="w-full"
              />
            </div>

            <div className="input-div input-dropdown">
              <label>Professor</label>
              <Dropdown
                value={selectedProfessor}
                onChange={(e) => setProfessor(e.value)}
                options={allProfessors}
                optionLabel="nome"
                placeholder="Selecione um professor"
                className="w-full"
                disabled={
                  selectedSubject && selectedSubject.professores?.length === 1
                }
              />
            </div>
          </div>

          <div className="form-row">
            <div className="input-div input-dropdown">
              <label>Dia da Semana</label>
              <Dropdown
                value={selectedWeekday}
                onChange={(e) => setWeekday(e.value)}
                options={allWeekdays}
                optionLabel="nome"
                placeholder="Selecione um dia da semana"
                className="w-full"
              />
            </div>

            <div className="input-div input-dropdown">
              <label>Horário</label>
              <Dropdown
                value={selectedTimeSlot}
                onChange={(e) => setTimeSlot(e.value)}
                options={allTimeSlots}
                optionLabel="descricao"
                placeholder="Selecione um horário"
                className="w-full"
                itemTemplate={timeSlotTemplate}
                valueTemplate={timeSlotTemplate}
              />
            </div>
          </div>

          <div className="buttons-div">
            <Button
              label="Cadastrar"
              onClick={registerLesson}
              loading={loading}
              rounded
              size="large"
              className="thematic"
            />
            <Button
              className="view-data-button thematic"
              icon="pi pi-list"
              tooltip="Ver Detalhes do Formulário"
              onClick={() => setDetails(true)}
            />
          </div>
        </div>
      </section>

      <Dialog
        className="lesson-data-dialog"
        header="Formulário"
        visible={details}
        style={{ width: "50vw" }}
        onHide={() => setDetails(false)}
      >
        <div className="data-dialog-item">
          <h3>Disciplina</h3>
          <span>
            {selectedSubject ? selectedSubject.nome : "Não selecionada"}
          </span>
        </div>
        <div className="data-dialog-item">
          <h3>Turma</h3>
          <span>{selectedClass ? selectedClass.nome : "Não selecionada"}</span>
        </div>
        <div className="data-dialog-item">
          <h3>Sala</h3>
          <span>
            {selectedClassroom
              ? `${selectedClassroom.nome} (Capacidade: ${selectedClassroom.capacidade})`
              : "Não selecionada"}
          </span>
        </div>
        <div className="data-dialog-item">
          <h3>Professor</h3>
          <span>
            {selectedProfessor ? selectedProfessor.nome : "Não selecionado"}
          </span>
        </div>
        <div className="data-dialog-item">
          <h3>Dia da Semana</h3>
          <span>
            {selectedWeekday ? selectedWeekday.nome : "Não selecionado"}
          </span>
        </div>
        <div className="data-dialog-item">
          <h3>Horário</h3>
          <span>
            {selectedTimeSlot
              ? `${selectedTimeSlot.inicio} - ${selectedTimeSlot.fim}`
              : "Não selecionado"}
          </span>
        </div>
      </Dialog>
    </>
  );
};

export default AddLessonPage;
