package com.learning.expenses.services;

import com.learning.expenses.domain.User;
import com.learning.expenses.exceptions.EtAuthException;
import com.learning.expenses.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern format = Pattern.compile("^(.+)@(.+)$");
        if(email != null){
            email = email.toLowerCase();
        }
        if(!format.matcher(email).matches()){
            throw new EtAuthException("Invalid email format");
        }
        Integer count = userRepository.getCountByEmail(email);
        if(count > 0){
            throw new EtAuthException("Email is already taken");
        }
        Integer userId = userRepository.create(firstName,lastName,email,password);

        return userRepository.findById(userId);
    }
}
