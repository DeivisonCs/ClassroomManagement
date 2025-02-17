import React, { useState } from "react";

import './styles.css';

import {Button} from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';

const LogInPage:React.FC = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');


    return (
    <section id="login-section">
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

                <Button label="Logar" rounded size="large"/>
            </div>
        </div>        
    </section>
    );
}

export default LogInPage;