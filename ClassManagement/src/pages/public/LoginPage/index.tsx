import React, { useRef, useState } from "react";

import './styles.css';

import {Button} from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Toast } from 'primereact/toast';

enum ToastSeverity {
    SUCCESS = 'success',
    ERROR = 'error'
}
        

const LogInPage:React.FC = () => {
    const toast = useRef<Toast>(null);
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    const show = (severity: ToastSeverity, summary: string, message: string) => {
        toast.current?.show({ severity: severity, summary: summary, detail: message });
    };

    function login() {
        //TODO
        // chamar back-end e receber o token aqui

        show(ToastSeverity.SUCCESS, 'Logado', 'Bem Vindo!'); // Usar retorno do back pra editar a mensagem
    }

    return (
    <section id="login-section">
        <Toast ref={toast} />
        <div className="logo-img-div">
            <img src="/SchoolEmblem.png" alt="School Emblem" />
        </div>

        <div className="forms-div">
            <h1>Log In</h1>

            <div className="inputs-fields">
                <div>
                    <label htmlFor="email">Email</label>
                    <InputText
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                <div>
                    <label htmlFor="password">Senha</label>
                    <Password
                        id="password"
                        value={password}
                        feedback={false}
                        toggleMask={true}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>

                <Button onClick={() => login()} label="Logar" rounded size="large"/>
            </div>
        </div>        
    </section>
    );
}

export default LogInPage;