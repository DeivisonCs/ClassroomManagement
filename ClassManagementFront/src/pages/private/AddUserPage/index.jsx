import React, { useState } from "react";
import HeaderComponent from "../../../components/HeaderComponent";

import "./styles.css";
import { InputText } from "primereact/inputtext";

const AddUserPage = () => {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    return (
    <>
        <HeaderComponent />
        <section id="user-add-section">
            <div className="title-div">
                <h1 className="page-title main-page-title">Usuários</h1>
                <h2 className="page-title sub-page-title">Cadastar</h2>
            </div>

            <div id="user-form-div">
                <div>
                    <label htmlFor="nome">Nome</label>
                    <InputText
                        id="nome"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="email">Email</label>
                    <InputText
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="email">Email</label>
                    <InputText
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="email">Email</label>
                    <InputText
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
            </div>
        </section>
    </>
    )
}

export default AddUserPage;