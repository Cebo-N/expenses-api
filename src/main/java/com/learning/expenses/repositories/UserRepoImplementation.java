package com.learning.expenses.repositories;

import com.learning.expenses.domain.User;
import com.learning.expenses.exceptions.EtAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepoImplementation implements UserRepository{

    private static final String SQL_INSERT = "INSERT INTO USERS(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) " +
            "VALUES (NEXTVAL('USERS_SEQ'), ?, ?, ?, ?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM USERS WHERE EMAIL = ?";

    private static  final String SQL_FIND_BY_USER_ID = "SELECT * FROM USERS WHERE USER_ID = ? ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,firstName);
                ps.setString(2,lastName);
                ps.setString(3,email);
                ps.setString(4,password);
                return  ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch (Exception ex){
            throw new EtAuthException("Error occurred while creating an account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        try{
            return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL,Integer.class,new Object[]{email});
        }catch(Exception ex){
            throw new EtAuthException("Error occurred searching for the email");
        }
    }

    @Override
    public User findById(Integer userId) {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_USER_ID,userRowMapper,new Object[]{userId});
        }catch(Exception ex){
            throw new EtAuthException("Could not find user with id"+userId);
        }
    }
    private RowMapper<User> userRowMapper = ((results, index) ->{
        return new User(results.getInt("USER_ID"),
                results.getString("FIRST_NAME"),
                results.getString("LAST_NAME"),
                results.getString(("EMAIL")),
                results.getString("PASSWORD"),
                results.getDate("CREATED_AT")
        );
    });
}
