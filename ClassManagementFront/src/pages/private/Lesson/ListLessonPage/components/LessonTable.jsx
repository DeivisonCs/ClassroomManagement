import React from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Link } from "react-router-dom";
import { createColumnTemplates } from "../utils/columnTemplates.jsx";

const LessonTable = ({ 
  data, 
  loading, 
  onDelete, 
  onShowGroupDetails, 
  onTurmaClick 
}) => {
  const { 
    disciplinaTemplate,
    turmaTemplate,
    salaTemplate,
    diaTemplate,
    horarioTemplate,
    professorTemplate,
    actionsTemplate
  } = createColumnTemplates(onTurmaClick, onShowGroupDetails, onDelete);

  // const header = (
  //   <div className="table-header">
  //     <Link to="/ifba/add-lesson">
  //       <Button label="Nova Aula" icon="pi pi-plus" />
  //     </Link>
  //   </div>
  // );

  return (
    <div className="list-table-div">
      <DataTable
        value={data}
        emptyMessage="Nenhum registro encontrado."
        stripedRows
        showGridlines
        // header={header}
        tableStyle={{ minWidth: "80vw", maxWidth: "100vw" }}
        paginator
        rows={10}
        rowsPerPageOptions={[5, 10, 25, 50]}
        removableSort
        loading={loading}
        rowClassName={(data) => (data.isGroup ? "grouped-row" : "")}
      >
        <Column
          body={disciplinaTemplate}
          header="Disciplina"
          sortable
          style={{ minWidth: "10rem" }}
        />
        <Column
          body={turmaTemplate}
          header="Turma"
          sortable
          style={{ minWidth: "10rem" }}
        />
        <Column
          body={salaTemplate}
          header="Sala"
          sortable
          style={{ minWidth: "10rem" }}
        />
        <Column
          body={diaTemplate}
          header="Dia da Semana"
          sortable
          style={{ minWidth: "10rem" }}
        />
        <Column
          body={horarioTemplate}
          header="Horário"
          sortable
          style={{ minWidth: "12rem" }}
        />
        <Column
          body={professorTemplate}
          header="Professor"
          sortable
          style={{ minWidth: "12rem" }}
        />
        <Column
          body={actionsTemplate}
          style={{ minWidth: "fit-content", maxWidth: "8rem" }}
        />
      </DataTable>
    </div>
  );
};

export default LessonTable;