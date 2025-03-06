import { useRef, useState, useEffect } from "react";

import "./styles.css";
import { InputText } from "primereact/inputtext";
import { InputMask } from "primereact/inputmask";
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import { createUser, listOccupations } from "../../../../services/userService";

const AddUserPage = () => {
    const toast = useRef(null);
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [cpf, setCpf] = useState('');
    const [role, setRole] = useState('');
    const [registration, setRegistration] = useState('');
    const [registrationMask, setRegistrationMask] = useState('');
    const [allRoles, setAllRoles] = useState([]);

    const roleMapping = {
        "TEACHER": "Professor",
        "STUDENT": "Estudante",
        "ADMIN": "Administrador",
    };

    const registrationMaskMapping = {
        "TEACHER": "9999999",
        "STUDENT": "99999999999",
        "ADMIN": "9999",
    };

    useEffect(() => {
        const fetchRoles = async () => {
            try {
                const roles = await listOccupations();
                setAllRoles(roles.map(role => ({
                    label: roleMapping[role.name] || role.name,
                    value: role.id
                })));
            } catch (error) {
                showToast('error', 'Erro', 'Ocorreu um erro ao carregar os cargos');
            }
        };

        fetchRoles();
    }, []);

    useEffect(() => {
        const selectedRole = allRoles.find(r => r.value === role);
        if (selectedRole) {
            const roleName = Object.keys(roleMapping).find(key => roleMapping[key] === selectedRole.label);
            setRegistrationMask(registrationMaskMapping[roleName] || '9999999');
        }
    }, [role, allRoles]);

    const showToast = (severity, summary, message) => {
        toast.current?.show({ severity: severity, summary: summary, detail: message, life: 3000 });
    };

    function isNotEmptyOrWhitespace(str) {
        return str.trim().length > 0;
    }
    function isValidEmail(email) {
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        return emailPattern.test(email);
    }
    function isValidRegistration(registration) {
        const registrationPattern = /^[0-9]{1,11}$/;
        return registrationPattern.test(registration);
    }    

    function removeCpfFormatting(cpf) {
        return cpf.replace(/[^\d]/g, '');
    }

    const validData = () => {
        if (!nome || !email || !cpf || !role || !registration)  {
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

        if(!isValidRegistration(registration)){
            showToast('info', 'Campo Inválido','Matrícula inválida!');
            return false;
        }

        return true;
    }

    function registerUser() {
        if(validData()){
            const formattedCpf = removeCpfFormatting(cpf);
            createUser(role, nome, email, formattedCpf, registration)
            .then(() => {
                showToast('success', 'Sucesso', 'Usuário cadastrado com sucesso!');
            })
            .catch(() => {
                showToast('error', 'Erro', 'Ocorreu um erro ao tentar cadastrar o usuário');
            });
        }
    }

    return (
    <>
        <Toast ref={toast} />
        <section id="user-add-section">
            <div className="title-div">
                <h1 className="page-title main-page-title">Usuários</h1>
                <h2 className="page-title sub-page-title">Cadastrar</h2>
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
                <div className="input-div">
                    <label>Matrícula</label>
                    <InputMask
                        id="registration"
                        value={registration}
                        onChange={(e) => setRegistration(e.target.value)}
                        mask={registrationMask}
                        slotChar="0"
                    />
                </div>

                <Button className="thematic" onClick={()  => registerUser()} label="Cadastrar" rounded size="large" />
            </div>
        </section>
    </>
    )
}

export default AddUserPage;