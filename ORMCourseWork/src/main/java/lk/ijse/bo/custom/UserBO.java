package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.models.StudentDTO;
import lk.ijse.models.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean save(UserDTO userDTO);

    public boolean update(UserDTO userDTO);
    public boolean delete(UserDTO object);

    public UserDTO get(String value);

    List<UserDTO> getAll();

    List<String> getIds();

    String getCurrentId();
    UserDTO getUsersIdAndPasswordAndRole(String userId, String password, String roll) throws SQLException;
}
