
package com.ifba.ms_user.config;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.ifba.ms_user.dto.UserForm;
import com.ifba.ms_user.models.enums.OccupationType;
import com.ifba.ms_user.services.AccountService;

@Configuration
public class AppConfig {

    @Autowired
    private AccountService accountService;

    @PostConstruct
    public void init() {
    	UserForm userForm = new UserForm(OccupationType.ADMINISTRADOR, "admin", "admin@gmail.com", "84963974088", "admin123");
        		
        accountService.registerAccount(userForm);
    }
}
