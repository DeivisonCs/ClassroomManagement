import React, { useRef, useState } from "react";
import './styles.css';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Toast } from 'primereact/toast';
import { useNavigate } from "react-router-dom";
import { authUser } from "../../../services/userService";

const LogInPage = () => {
    const toast = useRef(null);
    const navigate = useNavigate();
    const [user, setUser] = useState('');
    const [password, setPassword] = useState('');
    const [logged, setLogged] = useState(false);

    const show = (severity, summary, message) => {
        toast.current?.show({ severity: severity, summary: summary, detail: message, life: 3000 });
    };

    async function login() {
        try {
            const response = await authUser(user, password);

            if (response.token) {
                sessionStorage.setItem('token', response.token);
                setLogged(true);
                show('success', 'Logado', 'Bem Vindo!');
                console.log('Login bem-sucedido');
                setTimeout(() => {
                    navigate('/ifba/home');
                }, 2500);
            } else {
                show('error', 'Erro', 'Credenciais inválidas');
                console.log('Credenciais inválidas');
            }
        } catch (error) {
            show('error', 'Erro', 'Ocorreu um erro ao tentar logar');
            console.log('Erro ao tentar logar', error);
        }
    }

    return (
        <section id="login-section">
            <Toast ref={toast} />
            <div className={`logo-img-div ${logged ? 'emblem-animation-div' : ''}`}>
                <img className={logged ? 'emblem-animation' : ''} src="/SchoolEmblem.png" alt="School Emblem" />
            </div>

            <div className={`forms-div ${logged ? 'form-animation-div' : ''}`}>
                <h1 className={logged ? 'login-forms-animation' : ''}>Log In</h1>

                <div className={`inputs-fields ${logged ? 'login-forms-animation' : ''}`}>
                    <div>
                        <label htmlFor="user">User</label>
                        <InputText
                            id="user"
                            value={user}
                            onChange={(e) => setUser(e.target.value)}
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

                    <Button onClick={() => login()} label="Logar" rounded size="large" />
                </div>
            </div>
        </section>
    );
}

export default LogInPage;
