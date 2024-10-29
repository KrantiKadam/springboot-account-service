package com.demo.account_service.service;

import com.demo.account_service.entity.AccountEntity;
import com.demo.account_service.model.request.AccountRequest;
import com.demo.account_service.repository.AccountRepository;

import java.util.Optional;

//Commented this for now, as spring security pkg is removed from pom
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
    

    private final AccountRepository accountRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
    
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
      //Commented this for now, as spring security pkg is removed from pom
//        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    
    public AccountEntity createAccount(String username, String email, String password) {
    	AccountEntity account = new AccountEntity();
        account.setUsername(username);
        account.setEmail(email);
        // Hash the password before saving: 
        //Commented this for now, as spring security pkg is removed from pom
//        account.setPassword(passwordEncoder.encode(password));
        account.setPassword(password);
        return accountRepository.save(account);
    }
    
    public Optional<AccountEntity> getAccountById(Long id) {
        return  this.accountRepository.findById(id);
    }
    
    public boolean deleteAccountById(Long id) {
        if (this.accountRepository.existsById(id)) {
        	this.accountRepository.deleteById(id);
            return true;
        }
        return false;  // Account not found
    }
    
    public Optional<AccountEntity> updateAccountById(Long id, AccountRequest request) {
        return this.accountRepository.findById(id).map(account -> {
            account.setUsername(request.getUsername());
            account.setEmail(request.getEmail());
            this.accountRepository.save(account);
            return account;
        });
    }
    
    
}
