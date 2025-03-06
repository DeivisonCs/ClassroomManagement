import React, { useEffect, useRef, useState } from "react";
import { Toast } from "primereact/toast";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { MultiSelect } from "primereact/multiselect";
import { Dropdown } from "primereact/dropdown";
import { Dialog } from 'primereact/dialog';
import { useNavigate } from "react-router-dom";
import { classService } from "../../../../services/classService";
import { subjectService } from "../../../../services/subjectService";
import { studentService } from "../../../../services/studentService";

import './styles.css';

const AddClassPage = () => {
    const navigate = useNavigate();
    const toast = useRef(null);
    const [name, setName] = useState('');
    const [allSubjects, setAllSubjects] = useState([]);
    const [selectedSubject, setSubject] = useState(null);
    const [allStudents, setAllStudents] = useState([]);
    const [selectedStudents, setStudent] = useState([]);
    const [details, setDetails] = useState(false);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        loadData();
    }, []);
    
    const loadData = async () => {
        try {
            setLoading(true);
            // Carregar disciplinas e alunos em paralelo
            const [subjectsData, studentsData] = await Promise.all([
                subjectService.getAll(),
                studentService.getAll()
            ]);
            
            setAllSubjects(subjectsData || []);
            setAllStudents(studentsData || []);
        } catch (error) {
            console.error('Erro ao carregar dados:', error);
            showToast('error', 'Erro', 'Falha ao carregar dados iniciais');
        } finally {
            setLoading(false);
        }
    };

    const showToast = (severity, summary, message) => {
        toast.current?.show({ severity, summary, detail: message, life: 3000 });
    };

    const validData = () => {
        if (!name || name.trim() === '') {
            showToast('warn', 'Formulário Inválido', 'Por favor, preencha o nome da turma');
            return false;
        }

        if (!selectedSubject) {
            showToast('warn', 'Formulário Inválido', 'Por favor, selecione uma disciplina');
            return false;
        }

        return true;
    };

    const registerClass = async () => {
        if (!validData()) return;

        try {
            setLoading(true);
            
            const turmaData = {
                nome: name,
                disciplinaId: selectedSubject.id,
                alunosIds: selectedStudents.map(student => student.id || student.matricula)
            };
            
            console.log('Dados da turma a serem enviados:', turmaData);
            const novaTurma = await classService.create(turmaData);
            
            showToast('success', 'Sucesso', 'Turma cadastrada com sucesso!');
            
            setTimeout(() => {
                navigate('/class');
            }, 2000);
            
        } catch (error) {
            console.error('Erro ao cadastrar turma:', error);
            showToast('error', 'Erro', 'Falha ao cadastrar turma');
        } finally {
            setLoading(false);
        }
    };

    return(
    <>
        <Toast ref={toast}/>
        <section id="classroom-add-section">
            <div className="title-div">
                <h1 className="page-title main-page-title">Turmas</h1>
                <h2 className="page-title sub-page-title">Cadastrar</h2>
            </div>

            <div id="classroom-form-div">
                <div className="input-div">
                    <label htmlFor="nome">Nome</label>
                    <InputText
                        id="nome"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                </div>

                <div className="input-div input-dropdown">
                    <label>Disciplina</label>
                    <Dropdown
                        value={selectedSubject}
                        onChange={(e) => setSubject(e.value)}
                        options={allSubjects}
                        optionLabel="nome"
                        placeholder="Selecione uma disciplina"
                    />
                </div>
                
                <div className="input-div input-dropdown">
                    <label>Alunos</label>
                    <MultiSelect
                        value={selectedStudents}
                        onChange={(e) => setStudent(e.value)}
                        options={allStudents}
                        optionLabel="nome"
                        placeholder="Selecione os alunos"
                        maxSelectedLabels={3}
                    />
                </div>

                <div className="buttons-div">
                    <Button 
                        label="Cadastrar" 
                        onClick={registerClass}
                        loading={loading}
                        rounded 
                        size="large"/>
                    <Button 
                        className="view-data-button" 
                        icon="pi pi-list"
                        tooltip="Ver Detalhes do Formulário"
                        onClick={() => setDetails(true)}/>
                </div>
            </div>
        </section>

        <Dialog className="class-data-dialog" header="Formulário" visible={details} style={{ width: '50vw' }} onHide={() => setDetails(false)}>
            <div className="data-dialog-item">
                <h3>Nome</h3>
                <span>{name || 'Nome não selecionado'}</span>
            </div>  
            <div className="data-dialog-item">
                <h3>Disciplina</h3>
                <span>{selectedSubject ? selectedSubject.nome : 'Disciplina não selecionada'}</span>
            </div>
            <div className="data-dialog-item">
                <h3>Alunos</h3>
                <div className="selected-list">
                    {selectedStudents.length > 0? 
                        selectedStudents.map(student => 
                            <span key={student.matricula || student.id}>
                                <strong>Nome:</strong> {student.nome} <br/>
                                <strong>Matrícula:</strong> {student.matricula || student.id}
                            </span>
                        ) : 
                        <span>Nenhum aluno selecionado</span>
                    }
                </div>
            </div>
        </Dialog>
    </>
    );
};

export default AddClassPage;