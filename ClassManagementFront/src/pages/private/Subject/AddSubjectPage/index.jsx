import React, { useEffect, useRef, useState } from "react";

import "./styles.css";
import { InputText } from "primereact/inputtext";
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import { MultiSelect } from 'primereact/multiselect';
import { Dialog } from 'primereact/dialog';

const AddSubjectPage = () => {
    const toast = useRef(null);
    const [name, setName] = useState('');
    const [selectedProfessors, setProfessors] = useState([]);
    const [selectedClasses, setClasses] = useState([]);
    const [allProfessors, setAllProfessors] = useState([]);
    const [allClasses, setAllClasses] = useState([]);
    const [details, setDetails] = useState(false);

    useEffect(() => {
        setAllProfessors([
            {matricula:"124241", name: "Testando da Silva"},
            {matricula:"518523", name: "Arnoldo Martins"},
            {matricula:"518ra23", name: "Arnoldo Martins"},
            {matricula:"518dasd3", name: "Arnoldo Martins"},
            {matricula:"51da3", name: "Arnoldo Martins"},
            {matricula:"518523", name: "Arnoldo Martins"},
            {matricula:"518523", name: "Arnoldo Martins"},
        ])
        setAllClasses([
            {id: "15325", name:"Class 1"},
            {id: "95713", name:"Class 2"},
        ])
    }, [])

    const showToast = (severity, summary, message) => {
        toast.current?.show({ severity: severity, summary: summary, detail: message, life: 3000 });
    };

    const validData = () => {
        // TODO - validar os dados; Usar toast caso erro 

        return true;
    }

    function registerSubject() {
        if(validData()){
            console.log(name)
            console.log(selectedProfessors)
            console.log(selectedClasses)
        }
    }

    return (
    <>
        <Toast ref={toast} />
        <section id="subject-add-section">
            <div className="title-div">
                <h1 className="page-title main-page-title">Disciplinas</h1>
                <h2 className="page-title sub-page-title">Cadastar</h2>
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
                <div className="input-div input-dropdown">
                    <label>Turmas</label>
                    <MultiSelect
                        value={selectedClasses}
                        onChange={(e) => setClasses(e.value)}
                        options={allClasses}
                        optionLabel="name"
                    />
                </div>

                <div className="buttons-div">
                    <Button 
                        label="Cadastrar" 
                        onClick={()  => registerSubject()} 
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

        <Dialog className="data-dialog" header="Formulário" visible={details} style={{ width: '50vw' }} onHide={() => {if (!details) return; setDetails(false); }}>
            <div className="data-dialog-item">
                <h3>Nome</h3>
                <span>{name}</span>
            </div>  
            <div className="data-dialog-item">
                <h3>Professores</h3>
                <div className="selected-list">
                    {selectedProfessors.length > 0? 
                        selectedProfessors.map( professor => <span key={professor.matricula}>{professor.name}</span>) : <span>Nenhum professor selecionado</span>
                    }
                </div>
            </div>
            <div className="data-dialog-item">
                <h3>Turmas</h3>
                <div className="selected-list">
                    {selectedClasses.length > 0? 
                        selectedClasses.map( _class => <span key={_class.id}>{_class.name}</span>) : <span>Nenhum turma selecionada</span>
                    }
                </div>
            </div>

        </Dialog>
    </>
    )
}

export default AddSubjectPage;