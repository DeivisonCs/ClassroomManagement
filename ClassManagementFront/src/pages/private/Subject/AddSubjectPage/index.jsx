import React, { useEffect, useRef, useState } from "react";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { MultiSelect } from "primereact/multiselect";
import { Toast } from 'primereact/toast';
import { Dialog } from 'primereact/dialog';
import { subjectService } from "../../../../services/subjectService";
import { professorService } from "../../../../services/professorService";
import { useNavigate } from "react-router-dom";

import './styles.css';

const AddSubjectPage = () => {
    const navigate = useNavigate();
    const toast = useRef(null);
    const [name, setName] = useState('');
    const [allProfessors, setAllProfessors] = useState([]);
    const [selectedProfessors, setProfessors] = useState([]);
    const [details, setDetails] = useState(false);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        loadProfessors();
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
            
            const disciplinaData = {
                nome: name,
                professorMatricula: selectedProfessors.map(p => p.id)
            };
            
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

                <div className="buttons-div">
                    <Button 
                        className="thematic"
                        label="Cadastrar" 
                        onClick={registerSubject} 
                        loading={loading}
                        rounded 
                        size="large" />
                    <Button 
                        className="view-data-button thematic" 
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
        </Dialog>
    </>
    );
};

export default AddSubjectPage;