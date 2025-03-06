import React, { useState, useRef } from "react";
import "./styles.css";
import { InputText } from "primereact/inputtext";
import { InputNumber } from "primereact/inputnumber";
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import { classroomService } from "../../../../services/classroomService";

const AddClassroomPage = () => {
    const toast = useRef(null);
    const [nome, setNome] = useState('');
    const [capacidade, setCapacidade] = useState(null);
    const [loading, setLoading] = useState(false);

    const showToast = (severity, summary, message) => {
        toast.current?.show({ severity: severity, summary: summary, detail: message, life: 3000 });
    };

    const validData = () => {
        if (!nome) {
            showToast('info', 'Formulário Inválido', 'Por favor, preencha o nome da sala!');
            return false;
        }
        
        if (!capacidade || capacidade <= 0) {
            showToast('info', 'Formulário Inválido', 'Por favor, informe uma capacidade válida!');
            return false;
        }

        return true;
    };

    const registerClassroom = async () => {
        if (!validData()) return;

        try {
            setLoading(true);
            
            const salaData = {
                nome: nome,
                capacidade: capacidade
            };
            
            await classroomService.create(salaData);
            
            showToast('success', 'Sucesso', 'Sala cadastrada com sucesso!');
            
            // Limpar formulário após sucesso
            setNome('');
            setCapacidade(null);
            
        } catch (error) {
            showToast('error', 'Erro', 'Falha ao cadastrar sala');
            console.error(error);
        } finally {
            setLoading(false);
        }
    };

    return (
    <>
        <Toast ref={toast} />
        <section id="classroom-add-section">
            <div className="title-div">
                <h1 className="page-title main-page-title">Salas</h1>
                <h2 className="page-title sub-page-title">Cadastrar</h2>
            </div>

            <div id="classroom-form-div">
                <div className="input-div">
                    <label htmlFor="nome">Nome</label>
                    <InputText
                        id="nome"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                    />
                </div>
                <div className="input-div">
                    <label htmlFor="capacidade">Capacidade</label>
                    <InputNumber
                        id="capacidade"
                        value={capacidade}
                        onValueChange={(e) => setCapacidade(e.value)}
                        min={0}
                    />
                </div>

                <Button
                    className="thematic"
                    onClick={() => registerClassroom()} 
                    label="Cadastrar" 
                    loading={loading}
                    rounded 
                    size="large" />
            </div>
        </section>
    </>
    );
};

export default AddClassroomPage;