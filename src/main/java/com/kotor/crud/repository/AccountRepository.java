package com.kotor.crud.repository;

import com.kotor.crud.models.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepository {
    private List<Account> accounts = new ArrayList();

    public Account createNew(Account account) {
        accounts.add(account);
        return account;
    }

    public Account exists(Account account) {
        boolean exists = false;
        for(int i = 0; i < accounts.size(); i++) {
            if(account.getEmail().equalsIgnoreCase(accounts.get(i).getEmail())) {
                exists = true;
            }
        }
        if (exists) {
            return account;
        }
        return null;
    }
}
