package com.ifba.ms_user.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

import com.ifba.ms_user.dto.UserForm;
import com.ifba.ms_user.models.Occupation;
import com.ifba.ms_user.repositories.OccupationRepository;
import com.ifba.ms_user.services.AccountService;

@Component
public class PopulateIntialData implements CommandLineRunner {
    private final AccountService accountService;
    private final OccupationRepository occupationRepository;

    public PopulateIntialData(AccountService accountService, OccupationRepository occupationRepository) {
        this.accountService = accountService;
        this.occupationRepository = occupationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        occupationRepository.saveAll(List.of(
            new Occupation("ADMIN"),
            new Occupation("TEACHER"),
            new Occupation("STUDENT")
        ));
            		
        accountService.registerAccount(
            new UserForm(1L, "admin", "admin@gmail.com", "84963974088", "0000")
        );

        accountService.registerAccount(
            new UserForm(2L, "teacher", "teacher@gmail.com", "29105289017", "0000000")
        );

        accountService.registerAccount(
            new UserForm(3L, "student", "student@gmail.com", "49616929054", "00000000000")
        );
    }
}