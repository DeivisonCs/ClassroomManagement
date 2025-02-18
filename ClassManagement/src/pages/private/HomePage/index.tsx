import React from "react";
import HeaderComponent from "../../../components/HeaderComponent";

import "./styles.css";
import PageLinkComponent from "../../../components/PageOption/PageLinkComponent";

interface PageOptionProps {
    label: string
    icon: string
    linkTo: string
}

const HomePage:React.FC = () => {
    const pageOption:PageOptionProps[] = [
        {label: 'Disciplinas', icon:'book', linkTo:'/'},
        {label: 'Salas', icon:'building-columns', linkTo:'/'},
        {label: 'Usuários', icon:'user', linkTo:'/'}
    ]
    //TODO 
    // Verificar se o usuário está realmente logado, para bloquear acesso pela url

    return(
        <section id="home-section">
            <HeaderComponent/>

            <div id="page-options">
                {pageOption.map((option, idx) => {
                    return <PageLinkComponent key={idx} label={option.label} icon={option.icon} linkTo={option.linkTo}/>
                })}
            </div>
        </section>
    );

}

export default HomePage;