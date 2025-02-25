import React, { useRef, useState } from "react";
import HeaderComponent from "../../../../components/HeaderComponent";

import "./styles.css";
import { InputText } from "primereact/inputtext";
import { InputMask } from "primereact/inputmask";
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';

const AddSubjectPage = () => {
    const toast = useRef(null);
    const [nome, setNome] = useState('');
    // const [email, setEmail] = useState('');
    const [cpf, setCpf] = useState('');
    const [role, setRole] = useState('');
    const allRoles = ["Aluno", "Professor", "Admistrador"];

    const showToast = (severity, summary, message) => {
        toast.current?.show({ severity: severity, summary: summary, detail: message, life: 3000 });
    };

    const validData = () => {
        if (!nome || !email || !cpf || !role) {
            showToast('info', 'Formulário Inválido','Por favor, preencha todos os campos!');

            return false;
        }
 
        if(!isNotEmptyOrWhitespace(nome) || nome.length < 3){
            showToast('info', 'Campo Inválido','Nome inválido!');
            return false;
        }
        
        if(!isValidEmail(email)) {
            showToast('info', 'Campo Inválido','Email inválido!');
            return false;
        }

        return true;
    }

    function registerUser() {
        if(validData()){
            console.log(nome)
            console.log(cpf)
            console.log(email)
            console.log(role)
        }
    }

    return (
    <>
        <Toast ref={toast} />
        <section id="user-add-section">
            <div className="title-div">
                <h1 className="page-title main-page-title">Usrios</h1>
                <h2 className="page-title sub-page-title">Cadastar</h2>
            </div>

            <div id="user-form-div">
                <div className="input-div">
                    <label htmlFor="nome">Nome</label>
                    <InputText
                        id="nome"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                    />
                </div>
                <div className="input-div">
                    <label htmlFor="cpf">CPF</label>
                    <InputMask 
                        id="cpf" 
                        value={cpf} 
                        onChange={(e) => setCpf(e.target.value)} 
                        mask="999.999.999-99"/>
                </div>
                <div className="input-div">
                    <label htmlFor="email">Email</label>
                    <InputText
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="input-div role-dropdown">
                    <label>Cargo</label>
                    <Dropdown
                        value={role} 
                        onChange={(e) => setRole(e.value)} 
                        options={allRoles} 
                    />
                </div>

                <Button onClick={()  => registerUser()} label="Cadastrar" rounded size="large" />
            </div>
        </section>
    </>
    )
}

export default AddSubjectPage;