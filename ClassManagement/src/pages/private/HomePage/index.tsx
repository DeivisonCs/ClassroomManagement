import React, { useLayoutEffect, useState } from "react";
import HeaderComponent from "../../../components/HeaderComponent";

import "./styles.css";
import PageLinkComponent from "../../../components/PageOption/PageLinkComponent";
import { useNavigate, useParams } from "react-router-dom";

interface PageOptionProps {
    label: string
    icon: string
    linkTo: string
}

enum PageIdentifier {
    HOME = 'home',
    USERS = 'users',
    CLASSROOM = 'classroom'
}

const pageMenuOptions: Record<PageIdentifier, PageOptionProps[]> = {
    [PageIdentifier.HOME] : [
        {label: 'Disciplinas', icon:'book', linkTo:'/home'},
        {label: 'Salas', icon:'building-columns', linkTo:'/home'},
        {label: 'Usuários', icon:'user', linkTo:'/home'}
    ],
    [PageIdentifier.USERS] : [
        {label: 'Salas', icon:'building-columns', linkTo:'/home'},
        {label: 'Usuários', icon:'user', linkTo:'/home'}
    ],
    [PageIdentifier.CLASSROOM] : [],
}

const HomePage:React.FC = () => {
    const navigate = useNavigate();
    let {page} = useParams();
    const [pageOptions, setPageOptions] = useState<PageOptionProps[]>([]);
    const [pageTitle, setPageTitle] = useState<string>('');

    //TODO - Verificar se o usuário está realmente logado, para bloquear acesso pela url

    useLayoutEffect(() => {
        validUrl();
        capitalizePageTitle();
    }, []);

    const validUrl = () => {
        if(!Object.values(PageIdentifier).includes(page as PageIdentifier)) {
            console.log("Página não encontrada")
            navigate("/");
        }
        setPageOptions(pageMenuOptions[page as PageIdentifier || []])
    }

    function capitalizePageTitle() {
        let str = page!.toString();

        str.split(' ')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
            .join(' ');

        setPageTitle(str);
    }

    return(
        <section id="home-section">
            <HeaderComponent/>
            <h1>{pageTitle}</h1>

            <div id="page-options">
                {pageOptions.map((option, idx) => {
                    return <PageLinkComponent key={idx} label={option.label} icon={option.icon} linkTo={option.linkTo}/>
                })}
            </div>
        </section>
    );

}

export default HomePage;
