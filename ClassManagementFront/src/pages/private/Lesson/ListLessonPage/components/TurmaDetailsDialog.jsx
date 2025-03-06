import React from "react";
import { Dialog } from "primereact/dialog";

const TurmaDetailsDialog = ({ turma, visible, onHide }) => {
  if (!turma) return null;

  return (
    <Dialog
      header={`Detalhes da Turma: ${turma?.nome || ""}`}
      visible={visible}
      className="turma-details-dialog"
      style={{ width: "50vw" }}
      onHide={onHide}
    >
      <div className="turma-details">
        <div className="detail-section">
          <h3>Informações Gerais</h3>
          <p>
            <strong>Turma:</strong> {turma.nome}
          </p>
          <p>
            <strong>Disciplina:</strong> {turma.disciplina?.nome}
          </p>
        </div>

        <div className="detail-section">
          <h3>Alunos Matriculados</h3>
          {turma.alunos && turma.alunos.length > 0 ? (
            <ul className="student-list">
              {turma.alunos.map((aluno) => (
                <li key={aluno.matricula || aluno.id}>
                  <strong>{aluno.nome}</strong> (Matrícula:{" "}
                  {aluno.matricula || aluno.id})
                </li>
              ))}
            </ul>
          ) : (
            <p className="no-data-message">
              Nenhum aluno matriculado nesta turma.
            </p>
          )}
        </div>
      </div>
    </Dialog>
  );
};

export default TurmaDetailsDialog;