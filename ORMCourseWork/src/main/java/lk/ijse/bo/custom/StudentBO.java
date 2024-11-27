package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.entity.Student;
import lk.ijse.models.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {


    boolean save(StudentDTO studentDTO);
    //public boolean exsistCustomer(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(StudentDTO studentDTO);
    public boolean delete(StudentDTO object);
    public StudentDTO get(String value);

    List<StudentDTO> getAll();

    public StudentDTO searchById(String studentId);
    public boolean saveStudent(StudentDTO studentDTO);

    List<String> getIds();

    public List<StudentDTO> getAllStudents();

    String getCurrentId();
    Student getStudentById(String studentId) throws Exception;
   
}
