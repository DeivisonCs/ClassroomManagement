package com.ifba.ms_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.event.EventListener;

import com.ifba.ms_user.dto.UserForm;
import com.ifba.ms_user.services.AccountService;

@EnableDiscoveryClient
@SpringBootApplication
public class MsUserApplication {
	private final AccountService accountService;
	
	public MsUserApplication(AccountService accountService) {
        this.accountService = accountService;	
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MsUserApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		accountService.registerAccount(
				new UserForm(2L, "teacher", "teacher@gmail.com", "29105289017", "0000000")
		);
		
		accountService.registerAccount(
				new UserForm(3L, "student", "student@gmail.com", "49616929054", "00000000000")
		);
	}
}
