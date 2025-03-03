import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import LogInPage from "../pages/public/LoginPage";
import HomePage from "../pages/private/HomePage";
import AddUserPage from "../pages/private/User/AddUserPage";
import ListUserPage from "../pages/private/User/ListUserPage";
import HeaderComponent from "../components/HeaderComponent";
import AddSubjectPage from "../pages/private/Subject/AddSubjectPage";
import ListSubjectPage from "../pages/private/Subject/ListSubjectPage";
import AddClassroomPage from "../pages/private/Classroom/AddClassroomPage";
import ListClassroomPage from "../pages/private/Classroom/ListClassroomPage";

const AppRoutes = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LogInPage />} />
                <Route path="/ifba/:page" element={<><HeaderComponent/><HomePage /></>} />
                <Route path="/ifba/add-user" element={<><HeaderComponent/><AddUserPage/></>} />
                <Route path="/ifba/list-users" element={<><HeaderComponent/><ListUserPage/></>} />
                <Route path="/ifba/add-subject" element={<><HeaderComponent/><AddSubjectPage/></>} />
                <Route path="/ifba/list-subject" element={<><HeaderComponent/><ListSubjectPage/></>} />
                <Route path="/ifba/add-classroom" element={<><HeaderComponent/><AddClassroomPage/></>} />
                <Route path="/ifba/list-classroom" element={<><HeaderComponent/><ListClassroomPage/></>} />
            </Routes>
        </Router>
    );
}

export default AppRoutes;
