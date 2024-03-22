package com.example.library.mapping;


import com.example.library.user.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private ApplicationUser user;
    private String jwt;

}
