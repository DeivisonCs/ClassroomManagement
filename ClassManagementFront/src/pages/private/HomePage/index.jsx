import React, { useEffect, useState } from "react";
import HeaderComponent from "../../../components/HeaderComponent";
import "./styles.css";
import PageLinkComponent from "../../../components/PageOption/PageLinkComponent";
import { useNavigate, useParams } from "react-router-dom";

const pageMenuOptions = {
    home: [
        { label: 'Disciplinas', icon: 'book', linkTo: '/ifba/home' },
        { label: 'Salas', icon: 'building-columns', linkTo: '/ifba/home' },
        { label: 'Usuários', icon: 'user', linkTo: '/ifba/home' }
    ],
    users: [
        { label: 'Adicionar', icon: 'plus', linkTo: '/ifba/adduser' },
        { label: 'Listar', icon: 'list', linkTo: '/ifba/home' }
    ],
    classroom: []
};

const HomePage = () => {
    const navigate = useNavigate();
    let { page } = useParams();
    const [pageOptions, setPageOptions] = useState([]);
    const [pageTitle, setPageTitle] = useState('');

    //TODO - Verificar se o usuário está realmente logado, para bloquear acesso pela url

    useEffect(() => {
        validUrl();
        capitalizePageTitle();
    }, []);

    const validUrl = () => {
        if (!Object.keys(pageMenuOptions).includes(page)) {
            console.log("Página não encontrada");
            navigate("/");
        }
        setPageOptions(pageMenuOptions[page] || []);
    };

    const capitalizePageTitle = () => {
        let str = page.split(' ')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
            .join(' ');

        setPageTitle(str);
    };

    return (
        <>
            <HeaderComponent />
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
        </>
    );
};

export default HomePage;
