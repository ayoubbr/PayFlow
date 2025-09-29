package dao;

public interface IAuthDao {
    boolean authenticate(String email, String password);
}
