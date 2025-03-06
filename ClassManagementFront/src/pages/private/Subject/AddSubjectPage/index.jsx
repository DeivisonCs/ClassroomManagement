import { Button } from "primereact/button";
import { Dialog } from 'primereact/dialog';
import { InputText } from "primereact/inputtext";
import { MultiSelect } from "primereact/multiselect";
import { Toast } from 'primereact/toast';
import React, { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { classService } from "../../../../services/classService"; // Adicionado importação
import { professorService } from "../../../../services/professorService";
import { subjectService } from "../../../../services/subjectService";

import './styles.css';

const AddSubjectPage = () => {
    const navigate = useNavigate();
    const toast = useRef(null);
    const [name, setName] = useState('');
    const [allProfessors, setAllProfessors] = useState([]);
    const [selectedProfessors, setProfessors] = useState([]);
    const [allClasses, setAllClasses] = useState([]); // Novo estado para turmas
    const [selectedClasses, setSelectedClasses] = useState([]); // Novo estado para turmas selecionadas
    const [details, setDetails] = useState(false);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        loadProfessors();
        loadClasses(); // Carregar turmas ao inicializar
    }, []);

    const loadProfessors = async () => {
        try {
            const data = await professorService.getAll();
            const formattedProfessors = data.map(professor => ({
                id: professor.id,
                name: professor.nome,
                matricula: professor.matricula
            }));
            setAllProfessors(formattedProfessors);
        } catch (error) {
            console.error('Erro ao carregar professores:', error);
            showToast('error', 'Erro', 'Falha ao carregar lista de professores');
        }
    };

    // Nova função para carregar turmas
    const loadClasses = async () => {
        try {
            const data = await classService.getAll();
            setAllClasses(data);
        } catch (error) {
            console.error('Erro ao carregar turmas:', error);
            showToast('error', 'Erro', 'Falha ao carregar lista de turmas');
        }
    };

    const showToast = (severity, summary, message) => {
        toast.current?.show({ severity, summary, detail: message, life: 3000 });
    };

    const validData = () => {
        if (!name || name.trim() === '') {
            showToast('warn', 'Formulário Inválido', 'Por favor, preencha o nome da disciplina');
            return false;
        }

        return true;
    };

    const registerSubject = async () => {
        if (!validData()) return;
    
        try {
            setLoading(true);
            
            // Corrigir o formato dos dados
            const disciplinaData = {
                nome: name,
                professorMatricula: selectedProfessors.map(p => p.id),
                turmasIds: selectedClasses.map(c => c.id) // Enviar apenas os IDs das turmas
            };
            
            console.log("Enviando dados:", disciplinaData);
            
            await subjectService.create(disciplinaData);
            
            showToast('success', 'Sucesso', 'Disciplina cadastrada com sucesso!');
            
            setTimeout(() => {
                navigate('/subject');
            }, 2000);
            
        } catch (error) {
            showToast('error', 'Erro', 'Falha ao cadastrar disciplina');
            console.error(error);
        } finally {
            setLoading(false);
        }
    };

    return (
    <>
        <Toast ref={toast} />
        <section id="subject-add-section">
            <div className="title-div">
                <h1 className="page-title main-page-title">Disciplinas</h1>
                <h2 className="page-title sub-page-title">Cadastrar</h2>
            </div>

            <div id="subject-form-div">
                <div className="input-div">
                    <label htmlFor="nome">Nome</label>
                    <InputText
                        id="nome"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                </div>
                <div className="input-div input-dropdown">
                    <label>Professores</label>
                    <MultiSelect
                        value={selectedProfessors}
                        onChange={(e) => setProfessors(e.value)}
                        options={allProfessors}
                        optionLabel="name"
                    />
                </div>
                
                {/* Novo campo para seleção de turmas */}
                <div className="input-div input-dropdown">
                    <label>Turmas</label>
                    <MultiSelect
                        value={selectedClasses}
                        onChange={(e) => setSelectedClasses(e.value)}
                        options={allClasses}
                        optionLabel="nome"
                        placeholder="Selecione as turmas"
                        display="chip"
                    />
                </div>

                <div className="buttons-div">
                    <Button 
                        label="Cadastrar" 
                        onClick={registerSubject} 
                        loading={loading}
                        rounded 
                        size="large" />
                    <Button 
                        className="view-data-button" 
                        icon="pi pi-list"
                        tooltip="Ver Detalhes do Formulário"
                        onClick={() => setDetails(true)}/>
                </div>
            </div>
        </section>

        <Dialog className="subject-data-dialog" header="Formulário" visible={details} style={{ width: '50vw' }} onHide={() => setDetails(false)}>
            <div className="data-dialog-item">
                <h3>Nome</h3>
                <span>{name}</span>
            </div>  
            <div className="data-dialog-item">
                <h3>Professores</h3>
                <div className="selected-list">
                    {selectedProfessors.length > 0? 
                        selectedProfessors.map(professor => <span key={professor.matricula}>{professor.name}</span>)
                        : <span>Nenhum professor selecionado</span>
                    }
                </div>
            </div>
            
            {/* Adicionar seção de turmas selecionadas na dialog */}
            <div className="data-dialog-item">
                <h3>Turmas</h3>
                <div className="selected-list">
                    {selectedClasses.length > 0 ? 
                        selectedClasses.map(turma => <span key={turma.id}>{turma.nome}</span>)
                        : <span>Nenhuma turma selecionada</span>
                    }
                </div>
            </div>
        </Dialog>
    </>
    );
};

export default AddSubjectPage;