package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.models.StudentDTO;
import lk.ijse.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
   // private static CustomerDAOImpl customerDAOImpl;
   StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    public boolean save(StudentDTO customerDto) {
        return studentDAO.save(new Student(customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getTel(),customerDto.getEmail(),customerDto.getPayment()));
    }

    @Override
    public boolean update(StudentDTO customerDto) {
        return studentDAO.update( new Student(customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getTel(),customerDto.getEmail(),customerDto.getPayment()));
    }

    @Override
    public boolean delete(StudentDTO customerDto) {
        return studentDAO.delete(new Student(customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getTel(),customerDto.getEmail(),customerDto.getPayment()));
    }

    @Override
    public StudentDTO get(String value) {
        Student object = studentDAO.getObject(value);
        return new StudentDTO(object.getId(),object.getName(),object.getAddress(),object.getTel(),object.getEmail(),object.getPayment());
    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> all = studentDAO.getAll();
        for (Student student : all){
            studentDTOS.add(new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getTel(),student.getEmail(),student.getPayment()));
        }
        return studentDTOS;
    }
    @Override
    public List<String> getIds() {
        return studentDAO.getIds();
    }

    @Override
    public String getCurrentId() {
        return studentDAO.getCurrentId();
    }


}
