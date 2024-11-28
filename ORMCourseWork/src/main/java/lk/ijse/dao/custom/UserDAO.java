package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    User getUserById(String userId) throws SQLException; // Fetch a user by their ID
    String getPasswordHashByUserId(String userId) throws SQLException; // Get the password hash for the given userId
    List<String> getIds();
    String getCurrentId();
    User getObject(String value);
    User getUsersIdPasswordAndRole(String userId, String roll) throws SQLException;
}
