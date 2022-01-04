package com.learning.expenses.repositories;

import com.learning.expenses.domain.User;
import com.learning.expenses.exceptions.EtAuthException;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoImplementation implements UserRepository{


    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer userId) {
        return null;
    }
}
