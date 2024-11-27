package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.User;
import lk.ijse.models.StudentDTO;
import lk.ijse.entity.Student;
import lk.ijse.models.UserDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
   // private static CustomerDAOImpl customerDAOImpl;
   StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    public boolean save(StudentDTO studentDTO) {
        User user =studentDTO.getUser();

        User user1 = new User(user.getUserId());

        return studentDAO.save(new Student(studentDTO.getId(), user1, studentDTO.getName(), studentDTO.getAddress(), studentDTO.getEmail(), studentDTO.getTel()));
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        return studentDAO.update( new Student(studentDTO.getId(), studentDTO.getUser(), studentDTO.getName(),studentDTO.getAddress(),studentDTO.getTel(),studentDTO.getEmail()));
    }

    @Override
    public boolean delete(StudentDTO studentDTO) {
        return studentDAO.delete(new Student(studentDTO.getId(), studentDTO.getUser(), studentDTO.getName(),studentDTO.getAddress(),studentDTO.getTel(),studentDTO.getEmail()));
    }

    @Override
    public StudentDTO get(String value) {
        Student object = studentDAO.getObject(value);
        return new StudentDTO(object.getId(),object.getName(),object.getAddress(),object.getTel(),object.getEmail());
    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> all = studentDAO.getAll();
        for (Student student : all){
            studentDTOS.add(new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getTel(),student.getEmail()));
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

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> stList = new ArrayList<>();

        for (Student student : students){
            StudentDTO studentDTO = new StudentDTO(student.getId(),student.getUser(),student.getName(),student.getAddress(),student.getEmail(),student.getTel());
            stList.add(studentDTO);
        }
        return stList;
    }
    @Override
    public Student getStudentById(String studentId) throws Exception {
        Session session = null;
        try {
            // Open a session
            session = FactoryConfiguration.getInstance().getSession();

            // Create a query to fetch the Student by ID
            String hql = "FROM Student WHERE id = :studentId";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studentId", studentId);

            // Get the result (Single Student)
            Student student = query.uniqueResult();

            return student;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error retrieving student by ID.");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public StudentDTO searchById(String studentId) {
        Student student = studentDAO.search(studentId);
        return new StudentDTO(student.getId(),student.getUser(),student.getName(),student.getAddress(),student.getEmail(),student.getTel());
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getId(),studentDTO.getUser(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getEmail(),studentDTO.getTel()));
    }



}
