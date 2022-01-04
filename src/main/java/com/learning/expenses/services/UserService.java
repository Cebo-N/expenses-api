package com.learning.expenses.services;

import com.learning.expenses.domain.User;
import com.learning.expenses.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

}
