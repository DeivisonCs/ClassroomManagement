
package com.ifba.ms_user.dto;

import java.util.List;

import com.ifba.ms_user.models.Account;
import com.ifba.ms_user.models.Subject;

public record UserDetails (
    String registration,
    String occupation,
    String name,
    String email,
    String cpf,
    List<Subject> subject
) {
    public UserDetails(Account account) {
        this(
				account.getRegistration(), account.getOccupation().getName(),
				account.getPerson().getName(), account.getPerson().getEmail(),
				account.getPerson().getCpf(), account.getSubjects()
        );
    }
}
