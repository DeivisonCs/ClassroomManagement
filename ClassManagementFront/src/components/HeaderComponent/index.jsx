import React, { useState } from "react";
import './styles.css';
import { Link } from "react-router-dom";

const HeaderComponent = () => {
    const [menuActive, setMenuActive] = useState(false);
    const menuOptions = [
        {label: 'Perfil', icon: 'user', linkTo: ''},
        {label: 'Sair', icon: 'sign-out', linkTo: '/'}
    ];

    return (
        <header id="main-header">
            <Link to="/ifba/home" className="header-emblem-icon-span"><img src="/IFBA_ICON.ico" alt="IFBA icon" /></Link>

            <div 
                onClick={() => setMenuActive(prev => !prev)} 
                className={`header-menu-div ${menuActive ? 'active-menu' : ''}`}
            >
                <span></span>
                <span></span>
                <span></span>
            </div>

            <div className={`menu-options-div ${menuActive ? 'active-menu' : ''}`}>
                {menuOptions.map((option, index) => {
                    return ( 
                        <Link key={index} to={option.linkTo} className="menu-option">
                            <span className={`pi pi-${option.icon}`}></span>
                            {option.label}
                        </Link>
                    );
                })}
            </div>
        </header>
    );
};

export default HeaderComponent;
