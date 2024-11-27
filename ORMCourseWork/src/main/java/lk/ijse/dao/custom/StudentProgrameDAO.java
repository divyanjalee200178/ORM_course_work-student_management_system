package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Register;
import lk.ijse.models.RegisterDTO;
import org.hibernate.Session;

import java.util.List;

public interface StudentProgrameDAO extends CrudDAO<Register> {
    String getCurrentId();


//    boolean savePayment(Payment payment, Session session);
    boolean save(Register register);

//    boolean saveRegistration(List<RegisterDTO> registerDTOList);
    public boolean saveRegistration(List<Register> registrationList, Session session);



}
