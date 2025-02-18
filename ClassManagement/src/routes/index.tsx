import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import LogInPage from "../pages/public/LoginPage";
import HomePage from "../pages/private/HomePage";

const AppRoutes:React.FC = () => {
    return(
    <Router>
        <Routes>
            <Route path="/" element={<LogInPage/>}/>
            <Route path="/home" element={<HomePage/>}/>
        </Routes>
    </Router>
    )
}

export default AppRoutes;