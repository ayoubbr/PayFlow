package service;

import model.Agent;

import java.sql.SQLException;

public interface IAuthService {

    Agent login(String email, String password) throws SQLException;

}
