package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;
import lk.ijse.models.StudentDTO;
import lk.ijse.models.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
   UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    public boolean save(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getId(),userDTO.getName(),userDTO.getRole(),userDTO.getTel(),userDTO.getEmail(),userDTO.getPassword()));
    }


    @Override
    public boolean update(UserDTO userDTO) {
        return userDAO.update( new User(userDTO.getId(),userDTO.getName(),userDTO.getRole(),userDTO.getTel(),userDTO.getEmail(),userDTO.getPassword()));
    }

    @Override
    public boolean delete(UserDTO userDTO) {
        return userDAO.delete(new User(userDTO.getId(),userDTO.getName(),userDTO.getRole(),userDTO.getTel(),userDTO.getEmail(),userDTO.getPassword()));
    }
    @Override
    public UserDTO get(String value) {
        User object = userDAO.getObject(value);
        return new UserDTO(object.getId(),object.getName(),object.getRole(),object.getTel(),object.getEmail(),object.getPassword());
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> all = userDAO.getAll();
        for (User user : all){
            userDTOS.add(new UserDTO(user.getId(),user.getName(),user.getRole(),user.getTel(),user.getEmail(),user.getPassword()));
        }
        return userDTOS;
    }
    @Override
    public List<String> getIds() {
        return userDAO.getIds();
    }

    @Override
    public String getCurrentId() {
        return userDAO.getCurrentId();
    }

    @Override
    public UserDTO getUsersIdAndPasswordAndRole(String userId, String password, String roll) throws SQLException {
        User user = userDAO.getUsersIdPasswordAndRole(userId, password, roll);

        if (user != null) {
            return new UserDTO(user.getId(), user.getName(), user.getRole(), user.getTel(), user.getEmail(), user.getPassword());
        }
        return null;
    }

}
