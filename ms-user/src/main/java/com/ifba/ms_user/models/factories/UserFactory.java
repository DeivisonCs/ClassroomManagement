package com.ifba.ms_user.models.factories;

import com.ifba.ms_user.dto.UserForm;
import com.ifba.ms_user.models.Account;
import com.ifba.ms_user.models.Administrator;
import com.ifba.ms_user.models.Student;
import com.ifba.ms_user.models.Teacher;

public class UserFactory {
	public static Account createUser(UserForm form) {
        return switch (form.occupation()) {
            case STUDENT -> new Student(form.name(), form.email(), form.cpf(), form.enrollmentNumber());
            case TEACHER -> new Teacher(form.name(), form.email(), form.cpf(), form.teacherRegistrationNumber());
            case ADMIN -> new Administrator(form.name(), form.email(), form.cpf(), form.adminNumber());
        };
    }
	
	
}
