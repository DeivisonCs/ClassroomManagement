import React from "react";
import HeaderComponent from "../../../components/HeaderComponent";

import "./styles.css";

const HomePage:React.FC = () => {

    //TODO 
    // Verificar se o usuário está realmente logado, para bloquear acesso pela url

    return(
        <section id="home-section">
            <HeaderComponent/>
        </section>
    );

}

export default HomePage;