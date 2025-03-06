import React from "react";
import { Button } from "primereact/button";
import { Tooltip } from "primereact/tooltip";

export const createColumnTemplates = (onTurmaClick, onShowGroupDetails, onDelete) => {
  const disciplinaTemplate = (rowData) => {
    return rowData.disciplina ? rowData.disciplina.nome : "";
  };

  const turmaTemplate = (rowData) => {
    if (!rowData.turma) return "";

    return (
      <Button
        className="p-button-text p-button-plain"
        label={rowData.turma.nome}
        onClick={() => onTurmaClick(rowData.turma)}
      />
    );
  };

  const salaTemplate = (rowData) => {
    const sala = rowData.currentSchedule?.sala;
    if (!sala) return "Não atribuída";

    return (
      <div className="sala-with-tooltip">
        <Tooltip target=".sala-info" />
        <span
          className="sala-info"
          data-pr-tooltip={`Capacidade: ${sala.capacidade} alunos`}
          data-pr-position="top"
        >
          {sala.nome}
        </span>
      </div>
    );
  };

  const horarioTemplate = (rowData) => {
    if (rowData.isGroup) {
      return `${rowData.startTime} - ${rowData.endTime} (${rowData.lessonCount} aulas)`;
    }

    if (!rowData.currentSchedule?.horario) return "Não definido";

    const horario = rowData.currentSchedule.horario;
    return `${horario.inicio} - ${horario.fim}`;
  };

  const diaTemplate = (rowData) => {
    return rowData.currentSchedule?.diaSemana?.nome || "Não definido";
  };

  const professorTemplate = (rowData) => {
    return rowData.currentSchedule?.professor?.nome || "Não atribuído";
  };

  const actionsTemplate = (rowData) => {
    return (
      <div className="actions-div">
        {rowData.isGroup && (
          <Button
            tooltip="Ver Detalhes"
            tooltipOptions={{ position: "top" }}
            icon="pi pi-list"
            onClick={() => onShowGroupDetails(rowData)}
            className="p-button-info"
          />
        )}

        <Button
          className="tematic"
          tooltip="Remover Aula"
          tooltipOptions={{ position: "top" }}
          icon="pi pi-trash"
          onClick={() => onDelete(rowData)}
        />
      </div>
    );
  };

  return {
    disciplinaTemplate,
    turmaTemplate,
    salaTemplate,
    horarioTemplate,
    diaTemplate,
    professorTemplate,
    actionsTemplate
  };
};