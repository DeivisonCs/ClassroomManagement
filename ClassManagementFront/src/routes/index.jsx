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
import AddClassPage from "../pages/private/Class/AddClassPage";
import ListClassPage from "../pages/private/Class/ListClassPage";
import AddLessonPage from "../pages/private/Lesson/AddLessonPage";
import ListLessonPage from "../pages/private/Lesson/ListLessonPage";
import NotFoundPage from "../pages/public/NotFoundPage";

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
                <Route path="/ifba/add-class" element={<><HeaderComponent/><AddClassPage/></>} />
                <Route path="/ifba/list-class" element={<><HeaderComponent/><ListClassPage/></>} />
                <Route path="/ifba/add-lesson" element={<><HeaderComponent/><AddLessonPage/></>} />
                <Route path="/ifba/list-lesson" element={<><HeaderComponent/><ListLessonPage/></>} />
                <Route path="*" element={<NotFoundPage />} />
            </Routes>
        </Router>
    );
}

export default AppRoutes;
