import { Dialog } from "primereact/dialog";
import { useEffect, useState } from "react";
import { classService } from "../../../../../services/classService";

export default function TurmaDetailsDialog({ turma, visible, onHide }) {
  const [turmaDetalhada, setTurmaDetalhada] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (visible && turma) {
      carregarDetalhesTurma(turma.id);
    }
  }, [visible, turma]);

  const carregarDetalhesTurma = async (turmaId) => {
    try {
      setLoading(true);
      const detalhes = await classService.getById(turmaId);
      setTurmaDetalhada(detalhes);
    } catch (error) {
      console.error("Erro ao carregar detalhes da turma:", error);
    } finally {
      setLoading(false);
    }
  };

  const renderizarAlunos = () => {
    if (!turmaDetalhada || !turmaDetalhada.alunos || turmaDetalhada.alunos.length === 0) {
      return <p>Nenhum aluno matriculado nesta turma.</p>;
    }

    return (
      <ul className="student-list">
        {turmaDetalhada.alunos.map((aluno) => (
          <li key={aluno.matricula}>
            <strong>Nome:</strong> {aluno.nome}
            <br />
            <strong>Matrícula:</strong> {aluno.matricula}
          </li>
        ))}
      </ul>
    );
  };

  return (
    <Dialog
      header={`Detalhes da Turma: ${turma?.nome || ""}`}
      visible={visible}
      style={{ width: "50vw" }}
      onHide={onHide}
    >
      {loading ? (
        <p>Carregando detalhes da turma...</p>
      ) : (
        <div className="turma-details">
          <h3>Informações Gerais</h3>
          <p><strong>Turma:</strong> {turmaDetalhada?.nome || turma?.nome}</p>
          <p><strong>Disciplina:</strong> {turmaDetalhada?.disciplina?.nome || turma?.disciplina?.nome}</p>

          <h3>Alunos Matriculados</h3>
          {renderizarAlunos()}
        </div>
      )}
    </Dialog>
  );
}