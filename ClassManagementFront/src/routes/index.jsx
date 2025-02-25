import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import LogInPage from "../pages/public/LoginPage";
import HomePage from "../pages/private/HomePage";
import AddUserPage from "../pages/private/User/AddUserPage";
import ListUserPage from "../pages/private/User/ListUserPage";
import HeaderComponent from "../components/HeaderComponent";

const AppRoutes = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LogInPage />} />
                <Route path="/ifba/:page" element={<><HeaderComponent/><HomePage /></>} />
                <Route path="/ifba/adduser" element={<><HeaderComponent/><AddUserPage/></>} />
                <Route path="/ifba/listusers" element={<><HeaderComponent/><ListUserPage/></>} />
            </Routes>
        </Router>
    );
}

export default AppRoutes;
