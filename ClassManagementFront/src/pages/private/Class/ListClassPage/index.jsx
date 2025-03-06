import React, { useEffect, useState, useRef } from "react";
import "./styles.css";
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from "primereact/button";
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';
import { Dialog } from 'primereact/dialog';
import { Toast } from 'primereact/toast';
import { Link } from "react-router-dom";
import { classService } from "../../../../services/classService";

const ListClassPage = () => {
    const [classes, setClasses] = useState([]);
    const [selectedClass, setClass] = useState(null);
    const [details, setDetails] = useState(false);
    const [loading, setLoading] = useState(false);
    const toast = useRef(null);

    useEffect(() => {
        loadClasses();
    }, []);

    const loadClasses = async () => {
        try {
            setLoading(true);
            console.log('Iniciando chamada para buscar turmas');
            const data = await classService.getAll();
            console.log('Turmas recebidas:', data);
            setClasses(data);
        } catch (error) {
            console.error('Erro ao buscar turmas:', error);
            showToast('error', 'Erro', 'Falha ao carregar turmas');
        } finally {
            setLoading(false);
        }
    };

    const showToast = (severity, summary, detail) => {
        toast.current?.show({ severity, summary, detail, life: 3000 });
    };

    const viewDetails = (_class) => {
        setClass(_class);
        setDetails(true);
    };

    const actions = (rowData) => {
        return (
            <div className="actions-div">
                <Button 
                    className="tematic"
                    tooltip="Remover Turma" 
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
    };

    const removeClass = async (id) => {
        try {
            await classService.delete(id);
            showToast('success', 'Sucesso', 'Turma removida com sucesso');
            loadClasses();
        } catch (error) {
            console.error('Erro ao remover turma:', error);
            showToast('error', 'Erro', 'Falha ao remover turma');
        }
    };

    const confirmDelete = (_class) => {
        confirmDialog({
            message: `Deseja remover a turma ${_class.nome}?`,
            header: 'Confirmação',
            icon: 'pi pi-info-circle',
            defaultFocus: 'reject',
            accept: () => removeClass(_class.id),
            reject: null
        });
    };
    
    const header = (
        <div className="table-header">
            <Link to="/classes/add">
                <Button label="Nova Turma" icon="pi pi-plus" />
            </Link>
        </div>
    );

    return(
    <>
    <Toast ref={toast} />
    <ConfirmDialog />
    <section id="list-page">
        <div className="title-div">
            <h1 className="page-title main-page-title">Turmas</h1>
            <h2 className="page-title sub-page-title">Listar</h2>
        </div>

        <div className="list-table-div" >
            <DataTable 
                value={classes}
                loading={loading}
                emptyMessage="Nenhum registro encontrado."
                stripedRows
                showGridlines 
                header={header}
                tableStyle={{ minWidth: '80vw', maxWidth: '100vw'}}
                paginator 
                rows={5}
                rowsPerPageOptions={[5, 10, 25, 50]}
                removableSort
                >
                <Column field="nome" sortable header="Nome" style={{ minWidth: '12rem' }} />
                <Column 
                    header="Disciplina" 
                    field="disciplina.nome" sortable
                    style={{ minWidth: '12rem' }} /> 
                <Column body={(rowData) => actions(rowData)} style={{ minWidth: 'fit-content', maxWidth:'12rem' }} />
            </DataTable>
        </div>
    </section>

    <Dialog className="class-details-data-dialog" header="Detalhes da Turma" visible={details} style={{ width: '50vw' }} onHide={() => {if (!details) return; setDetails(false); }}>
        {selectedClass && (
            <>
                <div className="data-dialog-item">
                    <h3>Nome</h3>
                    <span>{selectedClass.nome}</span>
                </div>  
                <div className="data-dialog-item">
                    <h3>Disciplina</h3>
                    <span>{selectedClass.disciplina?.nome}</span>
                </div>
                <div className="data-dialog-item">
                    <h3>Alunos</h3>
                    <div className="selected-list">
                        {selectedClass.alunos?.length > 0 ? 
                            selectedClass.alunos.map(student => 
                                <span key={student.matricula || student.id}>
                                    <strong>Nome:</strong> {student.nome} <br/>
                                    <strong>Matrícula:</strong> {student.matricula || student.id}
                                </span>
                            ) : 
                            <span>Esta turma não possui alunos</span>
                        }
                    </div>
                </div>
            </>
        )}
    </Dialog>
    </>
    );
};

export default ListClassPage;