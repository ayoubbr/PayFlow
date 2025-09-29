package dao.impl;

import dao.IAuthDao;

public class AuthDaoImpl implements IAuthDao {
    @Override
    public boolean authenticate(String email, String password) {
        return true;
    }
}
