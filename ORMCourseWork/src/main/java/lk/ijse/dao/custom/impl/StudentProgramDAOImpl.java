package lk.ijse.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.StudentProgrameDAO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Program;
import lk.ijse.entity.Register;
import lk.ijse.entity.Student;
import lk.ijse.models.RegisterDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class StudentProgramDAOImpl implements StudentProgrameDAO {



    @Override
    public boolean save(Register registration) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object isSaved = session.save(registration);

        if(isSaved != null){
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }



    @Override
    public boolean update(Register object) {
        return false;
    }

    @Override
    public boolean delete(Register object) {
        return false;
    }

    @Override
    public Register get(Register object) {
        return null;
    }

    @Override
    public List<Register> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Register ");
        List<Register> registrations = query.list();
        transaction.commit();
        session.close();
        return registrations;
    }

    @Override
    public Register search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Register where register_id =?1");
        query.setParameter(1, id);
        Register registration = (Register) query.uniqueResult();
        transaction.commit();
        return registration;
    }

    @Override
    public String getCurrentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select id from Register order by id desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }





    public boolean savePayment(Payment payment, Session session) {
        try {
            session.save(payment);  // Hibernate saves the payment entity
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    public String generateRegisterId() {
        return UUID.randomUUID().toString(); // Generates a unique UUID as a string
    }
//    Register register = new Register();
//    register.setRegister_id(generateRegisterId());

    public boolean saveRegistration(List<Register> registrationList, Session session) {
        Transaction transaction = null;
        try {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Save the Register object
            session.save(registrationList);

            // Commit the transaction
            transaction.commit();
            return true;  // Registration successfully saved
        } catch (HibernateException e) {
            // Rollback the transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();  // Log the error (can use logging frameworks)
            return false;  // Error occurred
        }
    }




}
