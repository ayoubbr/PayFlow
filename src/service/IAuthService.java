package service;

import model.Agent;

import java.sql.SQLException;

public interface IAuthService {

    boolean authenticate(String email, String password);
    Agent login(String email, String password) throws SQLException;

}
