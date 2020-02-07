package com.kotor.crud.contollers;

import com.kotor.crud.exceptions.AccountAlreadyExists;
import com.kotor.crud.exceptions.BadRequest;
import com.kotor.crud.models.Account;
import com.kotor.crud.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private AccountRepository repository;

    @PostMapping("/accounts/registration")
    public Account registration(@RequestBody Optional<Account> account) throws AccountAlreadyExists, BadRequest {
        if(account.isPresent()) {
            Optional<Account> tempAccount = Optional.ofNullable(repository.exists(account.get()));
            if (tempAccount.isPresent()) {
                throw new AccountAlreadyExists();
            }
            return repository.createNew(account.get());
        }
        throw new BadRequest();
    }

    @GetMapping("/accounts/login")
    public Account login(@RequestBody Optional<Account> account) throws BadRequest {
        if(account.isPresent()) {

        }
        throw new BadRequest();
    }
}
