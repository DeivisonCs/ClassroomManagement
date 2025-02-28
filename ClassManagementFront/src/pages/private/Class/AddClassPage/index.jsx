import React, { useRef, useState } from "react";

import "./styles.css";
import { InputText } from "primereact/inputtext";
import { InputNumber } from 'primereact/inputnumber';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';

const AddClassPage = () => {
    const toast = useRef(null);
    const [nome, setNome] = useState('');
    const [capacity, setCapacity] = useState(0);

    const showToast = (severity, summary, message) => {
        toast.current?.show({ severity: severity, summary: summary, detail: message, life: 3000 });
    };

    function isNotEmptyOrWhitespace(str) {
        return str.trim().length > 0;
    } 

    const validData = () => {
        if (!nome || capacity == null) {
            showToast('info', 'Formulário Inválido','Por favor, preencha todos os campos!');

            return false;
        }
 
        if(!isNotEmptyOrWhitespace(nome) || nome.length < 3){
            showToast('info', 'Campo Inválido','Nome inválido!');
            return false;
        }

        return true;
    }

    function registerClass() {
        if(validData()){
            console.log(nome)
            console.log(capacity)
        }
    }

    return (
    <>
        <Toast ref={toast} />
        <section id="class-add-section">
            <div className="title-div">
                <h1 className="page-title main-page-title">Salas</h1>
                <h2 className="page-title sub-page-title">Cadastar</h2>
            </div>

            <div id="class-form-div">
                <div className="input-div">
                    <label htmlFor="nome">Nome</label>
                    <InputText
                        id="nome"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                    />
                </div>
                <div className="input-div">
                    <label htmlFor="capacity">Capacidade</label>
                    <InputNumber 
                        inputId="capacity" 
                        value={capacity} 
                        min={0}
                        onValueChange={(e) => setCapacity(e.value)} />
                </div>

                <Button onClick={()  => registerClass()} label="Cadastrar" rounded size="large" />
            </div>
        </section>
    </>
    )
}

export default AddClassPage;