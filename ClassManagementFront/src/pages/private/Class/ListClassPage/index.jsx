import React, { useEffect, useState } from "react";

import "./styles.css";

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from "primereact/button";
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';

const ListClassPage = () => {
    const [classes, setClasses] = useState([]);
    
    useEffect(() =>{
        setClasses([
            {
                id:"412", 
                nome: 'class 1', 
                capacidade: 210,
            },
            {
                id:"155", 
                nome: 'class 2', 
                capacidade: 20,
            },
            {
                id:"512", 
                nome: 'class 3', 
                capacidade: 10,
            },
        ]);
    }, [])

    const actions = (rowData) => {
        return (
            <div className="actions-div">
                <Button 
                    className="tematic"
                    tooltip="Remover Sala" 
                    tooltipOptions={{ position: 'top' }} 
                    icon="pi pi-trash"
                    onClick={() => confirmDelete(rowData)}/>
                {/* <Button icon="pi pi-times"></Button> */}
            </div>
        );
    }

    const removeClass = (id) => {
        // TODO - função pra remover disciplina
    }

    // dialog para confirmar remoção da disciplina
    const confirmDelete = (_class) => {
        confirmDialog({
            message: 'Deseja remover a sala '+ _class.nome +'?',
            header: 'Confirmação',
            icon: 'pi pi-info-circle',
            defaultFocus: 'reject',
            accept: () => removeClass(_class.id),
            reject: null
        });
    };

    return(
    <>
    <ConfirmDialog />
    <section id="list-page">
        <div className="title-div">
            <h1 className="page-title main-page-title">Salas</h1>
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
                <Column field="capacidade" sortable header="Capacidade" style={{ minWidth: '12rem' }}></Column>
                <Column body={(rowData) => actions(rowData)} style={{ minWidth: 'fit-content', maxWidth:'12rem' }} ></Column>
            </DataTable>
        </div>
    </section>
    </>
    );
}


export default ListClassPage;