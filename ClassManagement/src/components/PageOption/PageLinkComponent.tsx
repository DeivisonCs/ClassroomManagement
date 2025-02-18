import React from "react";

import './styles.css';
import { Link } from "react-router-dom";

interface PageOptionProps {
    label: string
    icon: string
    linkTo: string
}

const PageLinkComponent:React.FC<PageOptionProps> = (props) => {
    return (
    <div className="linkToPage-div">
        <Link to={props.linkTo} className="pagelink">
            <span className={`linkpage-icon pi pi-${props.icon}`}></span>
        </Link>
        <span className="linkpage-label">{props.label}</span>
    </div>
    )
}

export default PageLinkComponent;