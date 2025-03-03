import React, { useEffect, useRef, useState } from "react";
import { Toast } from "primereact/toast";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { MultiSelect } from "primereact/multiselect";
import { Dropdown } from "primereact/dropdown";
import { Dialog } from 'primereact/dialog';

import './styles.css';

const AddClassPage = () => {
    const toast = useRef(null);
    const [name, setName] = useState('');
    const [allSubjects, setAllSubjects] = useState([]);
    const [selectedSubject, setSubject] = useState('');
    const [allStudents, setAllStudents] = useState([]);
    const [selectedStudents, setStudent] = useState([]);
    const [details, setDetails] = useState(false);

    useEffect(() => {
        setAllStudents([
            {matricula:'1452', nome: 'Teste 1'},
            {matricula:'6136', nome: 'Teste 2'},
            {matricula:'5712', nome: 'Teste 3'},
            {matricula:'1854', nome: 'Teste 4'},
        ])
    
        setAllSubjects([
            {id:'5108', nome: 'Matemática'},
            {id:'9158', nome: 'Português'},
            {id:'5183', nome: 'Física'},
        ])
    }, []);

    const showToast = (severity, summary, message) => {
        toast.current?.show({ severity: severity, summary: summary, detail: message, life: 3000 });
    };

    const validData = () => {
        // TODO - validar os dados; Usar toast caso erro 

        if(!name || !selectedSubject){
            showToast('info', 'Formulário Inválido','Por favor, preencha todos os campos!');

            return false;
        }

        return true;
    }

    function registerClass() {
        if(validData()){
            console.log(name);
            console.log(selectedSubject);
            console.log(selectedStudents);
        }
    }

    return(
    <>
        <Toast ref={toast}/>
        <section id="classroom-add-section">
            <div className="title-div">
                <h1 className="page-title main-page-title">Salas</h1>
                <h2 className="page-title sub-page-title">Cadastar</h2>
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
                    />
                </div>
                
                <div className="input-div input-dropdown">
                    <label>Alunos</label>
                    <MultiSelect
                        value={selectedStudents}
                        onChange={(e) => setStudent(e.value)}
                        options={allStudents}
                        optionLabel="nome"
                        maxSelectedLabels={3}
                    />
                </div>

                <div className="buttons-div">
                    <Button 
                        label="Cadastrar" 
                        onClick={()  => registerClass()}
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

        <Dialog className="class-data-dialog" header="Formulário" visible={details} style={{ width: '50vw' }} onHide={() => {if (!details) return; setDetails(false); }}>
            <div className="data-dialog-item">
                <h3>Nome</h3>
                <span>{name?name:'Nome não selecionado'}</span>
            </div>  
            <div className="data-dialog-item">
                <h3>Disciplina</h3>
                <span>{selectedSubject?selectedSubject.nome:'Disciplina não selecionada'}</span>
            </div>
            <div className="data-dialog-item">
                <h3>Alunos</h3>
                <div className="selected-list">
                    {selectedStudents.length > 0? 
                        selectedStudents.map( student => 
                            <span key={student.matricula}>
                                <strong>Nome:</strong>{student.nome} <br/>
                                <strong>Matricula:</strong> {student.matricula} </span>) : 
                            <span>Nenhum aluno selecionado</span>
                    }
                </div>
            </div>
        </Dialog>
    </>
    );
}

export default AddClassPage;