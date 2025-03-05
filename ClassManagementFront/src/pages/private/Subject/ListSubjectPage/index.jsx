import React, { useEffect, useState, useRef } from "react";
import "./styles.css";
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from "primereact/button";
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';
import { subjectService } from "../../../../services/subjectService";
import { Toast } from 'primereact/toast';
import { Link } from "react-router-dom";

const ListSubjectPage = () => {
    const [subjects, setSubjects] = useState([]);
    const [loading, setLoading] = useState(false);
    const toast = useRef(null);

    useEffect(() => {
        loadSubjects();
    }, []);

    const loadSubjects = async () => {
        try {
            setLoading(true);
            console.log('Iniciando chamada para buscar disciplinas');
            const data = await subjectService.getAll();
            console.log('Disciplinas recebidas:', data);
            setSubjects(data);
        } catch (error) {
            console.error('Erro ao buscar disciplinas:', error);
            showToast('error', 'Erro', 'Falha ao carregar disciplinas');
        } finally {
            setLoading(false);
        }
    };

    const showToast = (severity, summary, detail) => {
        toast.current?.show({ severity, summary, detail, life: 3000 });
    };

    const renderProfessors = (rowData) => {
        if (!rowData.professores || !rowData.professores.length) return 'Sem professores';
        return rowData.professores.map(professor => professor.nome).join(", ");
    };

    const renderClasses = (rowData) => {
        if (!rowData.turmas || !rowData.turmas.length) return 'Sem turmas';
        return rowData.turmas.map(turma => turma.nome).join(", ");
    };

    const actions = (rowData) => {
        return (
            <div className="actions-div">
                <Button 
                    className="tematic"
                    tooltip="Remover Disciplina" 
                    tooltipOptions={{ position: 'top' }} 
                    icon="pi pi-trash"
                    onClick={() => confirmDelete(rowData)}/>
            </div>
        );
    };

    const removeSubject = async (id) => {
        try {
            await subjectService.delete(id);
            showToast('success', 'Sucesso', 'Disciplina removida com sucesso');
            loadSubjects();
        } catch (error) {
            showToast('error', 'Erro', 'Falha ao remover disciplina');
        }
    };

    const confirmDelete = (subject) => {
        confirmDialog({
            message: `Deseja remover ${subject.nome}?`,
            header: 'Confirmação',
            icon: 'pi pi-info-circle',
            defaultFocus: 'reject',
            accept: () => removeSubject(subject.id),
            reject: null
        });
    };

    const header = (
        <div className="table-header">
            <Link to="/subjects/add">
                <Button label="Nova Disciplina" icon="pi pi-plus" />
            </Link>
        </div>
    );

    return(
    <>
    <Toast ref={toast} />
    <ConfirmDialog />
    <section id="list-page">
        <div className="title-div">
            <h1 className="page-title main-page-title">Disciplinas</h1>
            <h2 className="page-title sub-page-title">Listar</h2>
        </div>

        <div className="list-table-div" >
            <DataTable 
                value={subjects}
                emptyMessage="Nenhum registro encontrado."
                stripedRows
                showGridlines
                header={header}
                tableStyle={{ minWidth: '80vw', maxWidth: '100vw'}}
                paginator 
                rows={5}
                rowsPerPageOptions={[5, 10, 25, 50]}
                removableSort
                loading={loading}
                >
                <Column field="nome" sortable header="Nome" style={{ minWidth: '12rem' }} />
                <Column header="Professores" body={renderProfessors} style={{ minWidth: '12rem' }} />
                <Column header="Turmas" body={renderClasses} style={{ minWidth: '12rem' }} />
                <Column body={(rowData) => actions(rowData)} style={{ minWidth: 'fit-content', maxWidth:'12rem' }} />
            </DataTable>
        </div>
    </section>
    </>
    );
};

export default ListSubjectPage; 