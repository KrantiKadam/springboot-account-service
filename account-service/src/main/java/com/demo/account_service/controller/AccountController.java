package com.demo.account_service.controller;


import com.demo.account_service.entity.AccountEntity;
import com.demo.account_service.service.AccountService;
import com.demo.account_service.model.request.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    // POST request for creating an account
    @PostMapping("/")
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountRequest request) {
    	AccountEntity account = accountService.createAccount(request.getUsername(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok(account);
    }
    
    // GET request for retrieving an account by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountEntity> getAccountrById(@PathVariable Long id) {
        // Retrieve account by ID
    	return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT request for updating an account by ID
    @PutMapping("/{id}")
    public ResponseEntity<AccountEntity> updateAccount(@PathVariable Long id, @RequestBody AccountRequest accountUpdateReq) {
        // Handle user update
    	return accountService.updateAccountById(id, accountUpdateReq)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE request for deleting an account by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        // Handle user deletion
    	 if (accountService.deleteAccountById(id)) {
             return ResponseEntity.noContent().build(); // Returns 204 No Content if deleted successfully
         } else {
             return ResponseEntity.notFound().build();  // Returns 404 Not Found if user not found
         }
    }

}
