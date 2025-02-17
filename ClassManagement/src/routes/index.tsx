import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import LogInPage from "../pages/public/LoginPage";

const AppRoutes:React.FC = () => {
    return(
    <Router>
        <Routes>
            <Route path="/" element={<LogInPage/>}/>
        </Routes>
    </Router>
    )
}

export default AppRoutes;