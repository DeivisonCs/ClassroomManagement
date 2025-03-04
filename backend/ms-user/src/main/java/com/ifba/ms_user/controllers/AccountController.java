
package com.ifba.ms_user.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifba.ms_user.dto.OccupationDto;
import com.ifba.ms_user.dto.UserDetails;
import com.ifba.ms_user.dto.UserForm;
import com.ifba.ms_user.dto.UserSummary;
import com.ifba.ms_user.services.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
@Validated
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    @Secured("ADMIN")
    public ResponseEntity<UserSummary> registerUser(@Valid @RequestBody UserForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.registerAccount(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetails> updateById(@PathVariable Long id, @RequestBody UserForm form) {
        return ResponseEntity.ok(accountService.updateById(id, form));
    }

    @DeleteMapping("/{id}")
    @Secured("ADMIN")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/occupations")
    @Secured("ADMIN")
    public ResponseEntity<List<OccupationDto>> listOccupations() {
		return ResponseEntity.ok(accountService.listOccupations());
	}
    
}
