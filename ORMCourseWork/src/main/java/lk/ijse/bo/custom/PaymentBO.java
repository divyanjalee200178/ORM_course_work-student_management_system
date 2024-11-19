package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.models.PaymentDTO;
import lk.ijse.models.StudentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean save(PaymentDTO paymentDTO);
    //public boolean exsistCustomer(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(PaymentDTO paymentDTO);
    public boolean delete(PaymentDTO object);
    public PaymentDTO get(String value);

    List<PaymentDTO> getAll();

    List<String> getIds();

    String getCurrentId();
}
