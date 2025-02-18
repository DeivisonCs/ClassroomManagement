import React, { useRef, useState } from "react";

import './styles.css';

import {Button} from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Toast } from 'primereact/toast';
import { useNavigate } from "react-router-dom";

enum ToastSeverity {
    SUCCESS = 'success',
    ERROR = 'error'
}
        

const LogInPage:React.FC = () => {
    const toast = useRef<Toast>(null);
    const navigate = useNavigate();
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [logged, setLogged] = useState(false);

    const show = (severity: ToastSeverity, summary: string, message: string) => {
        toast.current?.show({ severity: severity, summary: summary, detail: message });
    };

    //TODO
    // chamar back-end e receber o token aqui
    function login() {
        setLogged(true);
        
        show(ToastSeverity.SUCCESS, 'Logado', 'Bem Vindo!'); // Usar retorno do back pra editar a mensagem
        setTimeout(() => {
            navigate('/home');
        }, 2500);
    }

    return (
    <section id="login-section">
        <Toast ref={toast} />
        <div className={`logo-img-div ${logged?'emblem-animation-div':''}`}>
            <img className={logged?'emblem-animation':''} src="/SchoolEmblem.png" alt="School Emblem" />
        </div>

        <div className={`forms-div ${logged?'form-animation-div':''}`}>
            <h1 className={logged?'login-forms-animation':''}>Log In</h1>

            <div className={`inputs-fields ${logged?'login-forms-animation':''}`}>
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