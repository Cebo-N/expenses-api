package com.learning.expenses.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource {

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String,Object> userMap){
        String firstName = (String) userMap.get("firstname");
        String lastName = (String) userMap.get("lastname");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        return firstName + ", " + lastName + ", " + email + ", " + password;
    }

}