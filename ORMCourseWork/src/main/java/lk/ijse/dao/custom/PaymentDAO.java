package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Payment;
import org.hibernate.Session;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    List<String> getIds();
    String getCurrentId();
    Payment getObject(String value);

    public boolean savedPayment(Payment entity, Session session);

    boolean savePay(Payment payment);
}
