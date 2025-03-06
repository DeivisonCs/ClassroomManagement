import React, { useEffect, useState } from "react";
import "./styles.css";
import PageLinkComponent from "../../../components/PageOption/PageLinkComponent";
import { useNavigate, useParams } from "react-router-dom";

const pageMenuOptions = {
    home: [
        { label: 'Usuários', icon: 'user', linkTo: '/ifba/users' },
        { label: 'Salas', icon: 'building-columns', linkTo: '/ifba/classroom' },
        { label: 'Disciplinas', icon: 'book', linkTo: '/ifba/subject' },
        { label: 'Turmas', icon: 'users', linkTo: '/ifba/class' },
        { label: 'Aulas', icon: 'chalkboard-teacher', linkTo: '/ifba/lesson' },
    ],
    users: [
        { label: 'Adicionar', icon: 'plus', linkTo: '/ifba/add-user' },
        { label: 'Listar', icon: 'list', linkTo: '/ifba/list-users' }
    ],
    classroom: [
        { label: 'Adicionar', icon: 'plus', linkTo: '/ifba/add-classroom' },
        { label: 'Listar', icon: 'list', linkTo: '/ifba/list-classroom' }
    ],
    class: [
        { label: 'Adicionar', icon: 'plus', linkTo: '/ifba/add-class' },
        { label: 'Listar', icon: 'list', linkTo: '/ifba/list-class' }
    ],
    subject: [
        { label: 'Adicionar', icon: 'plus', linkTo: '/ifba/add-subject' },
        { label: 'Listar', icon: 'list', linkTo: '/ifba/list-subject' }
    ],
    lesson: [
        { label: 'Adicionar', icon: 'plus', linkTo: '/ifba/add-lesson' },
        { label: 'Listar', icon: 'list', linkTo: '/ifba/list-lesson' }
    ]
};

const HomePage = () => {
    const navigate = useNavigate();
    let { page } = useParams();
    const [pageOptions, setPageOptions] = useState([]);
    const [pageTitle, setPageTitle] = useState('');

    //TODO - Verificar se o usuário está realmente logado, para bloquear acesso pela url

    useEffect(() => {
        validUrl();
        validToken();
        setPageTitle(convertUrl());
    }, [page]);

    const validToken = () => {
        if(sessionStorage.getItem("token") == null)
            navigate("/");
    }

    const validUrl = () => {
        if (!Object.keys(pageMenuOptions).includes(page)) {
            console.log("Página não encontrada");
            navigate("/");
        }
        setPageOptions(pageMenuOptions[page] || []);
    };

    const convertUrl = () => {
        switch (page) {
            case "home": return "Home";
            case "users": return "Usuários";
            case "classroom": return "Salas";
            case "class": return "Turmas";
            case "subject": return "Disciplinas";
            case "lesson": return "Aulas"; 
            default: navigate("/");
        }
    }

    // const capitalizePageTitle = () => {
    //     let str = page.split(' ')
    //         .map(word => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
    //         .join(' ');

    //     setPageTitle(str);
    // };

    return (
    <section id="page-section">
        <h1 id="main-page-title">{pageTitle}</h1>

        <div id="page-options">
            {Array.isArray(pageOptions) && pageOptions.length !== 0 &&
                pageOptions.map((option, idx) => {
                    return <PageLinkComponent key={idx} label={option.label} icon={option.icon} linkTo={option.linkTo} />;
                })
            }
        </div>
    </section>
    );
};

export default HomePage;
