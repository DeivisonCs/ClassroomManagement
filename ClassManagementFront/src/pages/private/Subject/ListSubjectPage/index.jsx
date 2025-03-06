import { Button } from "primereact/button";
import { Column } from 'primereact/column';
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';
import { DataTable } from 'primereact/datatable';
import { Toast } from 'primereact/toast';
import React, { useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import { classService } from "../../../../services/classService"; // Adicionado
import { subjectService } from "../../../../services/subjectService";
import "./styles.css";

const ListSubjectPage = () => {
    const [subjects, setSubjects] = useState([]);
    const [loading, setLoading] = useState(false);
    const [classes, setClasses] = useState([]); // Estado para armazenar todas as turmas
    const toast = useRef(null);

    useEffect(() => {
        loadData();
    }, []);

    // Carregar disciplinas e turmas em uma única função
    const loadData = async () => {
        try {
            setLoading(true);
            
            // Carregar disciplinas e turmas em paralelo
            const [disciplinasData, turmasData] = await Promise.all([
                subjectService.getAll(),
                classService.getAll()
            ]);
            
            // Armazenar turmas em um estado separado
            setClasses(turmasData);
            
            // Processar disciplinas para incluir turmas associadas
            const disciplinasProcessadas = disciplinasData.map(disciplina => {
                // Encontrar todas as turmas desta disciplina
                const turmasAssociadas = turmasData.filter(turma => 
                    turma.disciplina?.id === disciplina.id
                );
                
                // Adicionar turmas à disciplina
                return {
                    ...disciplina,
                    turmasAssociadas: turmasAssociadas
                };
            });
            
            setSubjects(disciplinasProcessadas);
            
        } catch (error) {
            console.error('Erro ao carregar dados:', error);
            showToast('error', 'Erro', 'Falha ao carregar dados');
        } finally {
            setLoading(false);
        }
    };

    const showToast = (severity, summary, detail) => {
        toast.current?.show({ severity, summary, detail, life: 3000 });
    };

    const renderClasses = (rowData) => {
        // Usar as turmas associadas que foram processadas
        const turmasAssociadas = rowData.turmasAssociadas || [];
        
        if (turmasAssociadas.length === 0) return 'Sem turmas';
        
        return turmasAssociadas.map(turma => turma.nome).join(", ");
    };

    const actions = (rowData) => {
        return (
            <div className="actions-div">
                <Button 
                    className="thematic"
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
            loadData(); // Recarregar todos os dados
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

    // const header = (
    //     <div className="table-header">
    //         <Link to="/subjects/add">
    //             <Button label="Nova Disciplina" icon="pi pi-plus" />
    //         </Link>
    //     </div>
    // );

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
                // header={header}
                tableStyle={{ minWidth: '80vw', maxWidth: '100vw'}}
                paginator 
                rows={5}
                rowsPerPageOptions={[5, 10, 25, 50]}
                removableSort
                loading={loading}
                >
                <Column field="nome" sortable header="Nome" style={{ minWidth: '12rem' }} />
                <Column header="Turmas" body={renderClasses} style={{ minWidth: '12rem' }} />
                <Column body={(rowData) => actions(rowData)} style={{ minWidth: 'fit-content', maxWidth:'12rem' }} />
            </DataTable>
        </div>
    </section>
    </>
    );
};

export default ListSubjectPage;