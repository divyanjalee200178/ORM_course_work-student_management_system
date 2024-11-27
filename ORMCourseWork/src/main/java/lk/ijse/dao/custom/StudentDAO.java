package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Register;
import lk.ijse.entity.Student;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {

   List<String> getIds();
   String getCurrentId();
   Student getObject(String value);




}
