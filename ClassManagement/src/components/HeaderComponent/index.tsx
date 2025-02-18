import React, { useState } from "react";

import './styles.css';
import { Link } from "react-router-dom";

interface MenuOption {
    label: string,
    icon: string,
    linkTo: string
}

const HeaderComponent:React.FC = () => {
    const [menuActive, setMenuActive] = useState<boolean>(false);
    const menuOptions:MenuOption[] = [
        {label: 'Perfil', icon:'user' , linkTo: ''},
        {label: 'Sair', icon:'sign-out', linkTo: '/'}
    ]

    return(
        <header id="main-header">
            <span className="header-emblem-icon-span"><img src="/SchoolEmblem.ico" alt="School Emblem" /></span>

            <div 
                onClick={() => setMenuActive(prev => !prev)} 
                className={`header-menu-div ${menuActive? 'active-menu':''}`}
            >
                <span></span>
                <span></span>
                <span></span>
            </div>

            <div className={`menu-options-div ${menuActive? 'active-menu':''}`}>
                {menuOptions.map((option, index) => {
                    return( 
                        <Link key={index} to={option.linkTo} className="menu-option">
                            <span className={`pi pi-${option.icon}`}></span>
                            {option.label}
                        </Link>
                    )
                })}
            </div>
        </header>
    );

}

export default HeaderComponent;