import React, { useEffect, useState } from "react";

import "./styles.css";

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from "primereact/button";
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';
import { Dialog } from 'primereact/dialog';

const ListClassPage = () => {
    const [classes, setClasses] = useState([]);
    const [selectedClass, setClass] = useState('');
    const [details, setDetails] = useState(false);

    useEffect(() => {
        setClasses([
            {
                id:"412", 
                nome: 'class 1', 
                disciplina: 'Matemática',
                alunos: [
                    {matricula:"4512", nome:"aluno 1"},
                    {matricula:"8151", nome:"aluno 2"},
                ]
            },
            {
                id:"814", 
                nome: 'class 2', 
                disciplina: 'Portugues',
                alunos: [
                    {matricula:"05462", nome:"aluno 3"},
                    {matricula:"1261", nome:"aluno 4"},
                ]
            }
        ]);
    }, [])

    const viewDetails = (_class) => {
        setClass(_class);
        setDetails(true);
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
                <Button 
                    className="tematic"
                    tooltip="Ver detalhes" 
                    tooltipOptions={{ position: 'top' }} 
                    icon="pi pi-bars"
                    onClick={() => viewDetails(rowData)}/>
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
                value={classes}
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
                    header="Disciplina" 
                    field="disciplina" sortable
                    style={{ minWidth: '12rem' }}> 
                </Column>
                <Column body={(rowData) => actions(rowData)} style={{ minWidth: 'fit-content', maxWidth:'12rem' }} ></Column>
            </DataTable>
        </div>
    </section>

    <Dialog className="class-details-data-dialog" header="Formulário" visible={details} style={{ width: '50vw' }} onHide={() => {if (!details) return; setDetails(false); }}>
        <div className="data-dialog-item">
            <h3>Nome</h3>
            <span>{selectedClass.nome}</span>
        </div>  
        <div className="data-dialog-item">
            <h3>Disciplina</h3>
            <span>{selectedClass.nome}</span>
        </div>
        <div className="data-dialog-item">
            <h3>Alunos</h3>
            <div className="selected-list">
                {selectedClass && selectedClass.alunos.map( student => 
                    <span key={student.matricula}>
                        <strong>Nome:</strong>{student.nome} <br/>
                        <strong>Matricula:</strong> {student.matricula}
                    </span>)
                }
            </div>
        </div>
    </Dialog>
    </>
    );
}


export default ListClassPage;