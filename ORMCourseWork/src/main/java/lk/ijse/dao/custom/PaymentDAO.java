package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Student;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    List<String> getIds();
    String getCurrentId();
    Payment getObject(String value);
}
