import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import LogInPage from "../pages/public/LoginPage";
import HomePage from "../pages/private/HomePage";
import AddUserPage from "../pages/private/User/AddUserPage";
import ListUserPage from "../pages/private/User/ListUserPage";

const AppRoutes = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LogInPage />} />
                <Route path="/ifba/:page" element={<HomePage />} />
                <Route path="/ifba/adduser" element={<AddUserPage />} />
                <Route path="/ifba/listusers" element={<ListUserPage />} />
            </Routes>
        </Router>
    );
}

export default AppRoutes;
