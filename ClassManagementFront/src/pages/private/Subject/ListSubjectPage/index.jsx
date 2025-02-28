import React, { useEffect, useState } from "react";

import "./styles.css";

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from "primereact/button";
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';

const ListSubjectPage = () => {
    const [subjetcs, setSubjects] = useState([])

    useEffect(() => {
        setSubjects([
            {
                id:"412", 
                nome: 'subject 1', 
                professores: [
                    {id:"16123", nome:"Professor 1"}, 
                    {id:"94124", nome:"Professor 2"}, 
                ], 
                turmas: [
                    {id:"4512", nome:"Class 1"},
                    {id:"8151", nome:"Class 2"},
                ]
            },
            {
                id:"814", 
                nome: 'subject 2', 
                professores: [
                    {id:"5171", nome:"Professor 3"}, 
                    {id:"'56061'", nome:"Professor 4"}, 
                ], 
                turmas: [
                    {id:"05462", nome:"Class 3"},
                    {id:"1261", nome:"Class 4"},
                ]
            }
        ]);
    }, [])

    const renderProfessors = (professors) => {
        return professors.map(professor => professor.nome).join(", ");
    }

    const renderClasses = (classes) => {
        return classes.map(_class => _class.nome).join(", ");
    }

    const actions = (rowData) => {
        return (
            <div className="actions-div">
                <Button 
                    className="tematic"
                    tooltip="Remover Disciplina" 
                    tooltipOptions={{ position: 'top' }} 
                    icon="pi pi-trash"
                    onClick={() => confirmDelete(rowData)}/>
                {/* <Button icon="pi pi-times"></Button> */}
            </div>
        );
    }

    const removeSubject = (id) => {
        // TODO - função pra remover disciplina
    }

    // dialog para confirmar remoção da disciplina
    const confirmDelete = (subject) => {
        confirmDialog({
            message: 'Deseja remover '+ subject.nome +'?',
            header: 'Confirmação',
            icon: 'pi pi-info-circle',
            defaultFocus: 'reject',
            accept: () => removeSubject(subject.id),
            reject: null
        });
    };

    return(
    <>
    <ConfirmDialog />
    <section id="list-page">
        <div className="title-div">
            <h1 className="page-title main-page-title">Disciplinas</h1>
            <h2 className="page-title sub-page-title">Listar</h2>
        </div>

        <div className="list-table-div" >
            <DataTable 
                value={subjetcs}
                emptyMessage="Nenhum registro encontrado."
                stripedRows
                showGridlines 
                tableStyle={{ minWidth: '80vw',  maxWidth: '100vw'}}
                paginator 
                rows={5}
                rowsPerPageOptions={[5, 10, 25, 50]}
                removableSort
                >
                <Column field="nome" sortable header="Nome" style={{ minWidth: '12rem' }} ></Column>
                <Column 
                    header="Professores" 
                    body={(rowData) => renderProfessors(rowData.professores)} 
                    style={{ minWidth: '12rem' }}> 
                </Column>
                <Column 
                    header="Turmas" 
                    body={(rowData) => renderClasses(rowData.turmas)} 
                    field="turmas.nome" 
                    style={{ minWidth: '12rem' }}>
                </Column>
                <Column body={(rowData) => actions(rowData)} style={{ minWidth: 'fit-content', maxWidth:'12rem' }} ></Column>
            </DataTable>
        </div>
    </section>
    </>
    );
}


export default ListSubjectPage;