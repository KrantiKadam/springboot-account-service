package com.demo.account_service.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
	
	// DTO to handle account creation request payload
    private String username;
    private String email;
    private String password;
}
