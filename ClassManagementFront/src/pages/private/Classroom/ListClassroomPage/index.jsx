import React, { useState, useEffect, useRef } from "react";
import "./styles.css";
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from "primereact/button";
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';
import { Toast } from 'primereact/toast';
import { classroomService } from "../../../../services/classroomService";
import { Link } from "react-router-dom";

const ListClassroomPage = () => {
    const [classes, setClasses] = useState([]);
    const [loading, setLoading] = useState(false);
    const toast = useRef(null);

    // Esta função é crucial para carregar os dados reais
    const loadClassrooms = async () => {
        try {
            setLoading(true);
            console.log('Iniciando chamada para buscar salas');
            const data = await classroomService.getAll();
            console.log('Dados recebidos do backend:', data);
            
            // Esta linha é onde definimos os dados reais no estado
            setClasses(data);
            console.log('Estado atualizado com dados:', data);
        } catch (error) {
            console.error('Erro ao buscar salas:', error);
            showToast('error', 'Erro', 'Falha ao carregar salas');
        } finally {
            setLoading(false);
        }
    };

    // Este useEffect garante que os dados são carregados apenas uma vez na montagem
    useEffect(() => {
        // Removemos qualquer inicialização com dados mockados
        // Chamamos nossa função de carregamento
        loadClassrooms();
    }, []);

    const showToast = (severity, summary, detail) => {
        toast.current?.show({ severity, summary, detail, life: 3000 });
    };

    const actions = (rowData) => {
        return (
            <div className="actions-div">
                <Button 
                    className="thematic"
                    tooltip="Remover Sala" 
                    tooltipOptions={{ position: 'top' }} 
                    icon="pi pi-trash"
                    onClick={() => confirmDelete(rowData)}/>
            </div>
        );
    };

    const removeClass = async (id) => {
        try {
            await classroomService.delete(id);
            showToast('success', 'Sucesso', 'Sala removida com sucesso');
            loadClassrooms(); // Recarrega os dados após remoção
        } catch (error) {
            showToast('error', 'Erro', 'Falha ao remover sala');
        }
    };

    const confirmDelete = (_class) => {
        confirmDialog({
            message: `Deseja remover a sala ${_class.nome}?`,
            header: 'Confirmação',
            icon: 'pi pi-info-circle',
            defaultFocus: 'reject',
            accept: () => removeClass(_class.id),
            reject: null
        });
    };
    
    // Adicionado botão para adicionar novas salas
    // const header = (
    //     <div className="table-header">
    //         <Link to="/classrooms/add">
    //             <Button label="Nova Sala" icon="pi pi-plus" />
    //         </Link>
    //     </div>
    // );

    return(
    <>
    <Toast ref={toast} />
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
                // header={header}
                tableStyle={{ minWidth: '80vw', maxWidth: '100vw'}}
                paginator 
                rows={5}
                rowsPerPageOptions={[5, 10, 25, 50]}
                removableSort
                loading={loading}
                >
                <Column field="nome" sortable header="Nome" style={{ minWidth: '12rem' }} ></Column>
                <Column field="capacidade" sortable header="Capacidade" style={{ minWidth: '12rem' }}></Column>
                <Column body={(rowData) => actions(rowData)} style={{ minWidth: 'fit-content', maxWidth:'12rem' }} ></Column>
            </DataTable>
        </div>
    </section>
    </>
    );
};

export default ListClassroomPage;