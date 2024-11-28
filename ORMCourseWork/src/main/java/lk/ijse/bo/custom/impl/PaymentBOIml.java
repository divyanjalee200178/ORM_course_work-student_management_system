package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Register;
import lk.ijse.entity.Student;
import lk.ijse.models.PaymentDTO;
import lk.ijse.models.RegisterDTO;
import lk.ijse.models.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOIml implements PaymentBO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public boolean saves(PaymentDTO paymentDTO) {
        //return paymentDAO.savePay(new Payment(paymentDTO.getId(),paymentDTO.getFee(),paymentDTO.getRegisterFee(),paymentDTO.getTotalFee()));
        return false;
    }

    public boolean save(PaymentDTO paymentDTO) {
        RegisterDTO registerDTO = paymentDTO.getRegister();
        Register register =  new Register(registerDTO.getRegister_id());

        return paymentDAO.save(new Payment(paymentDTO.getId(),paymentDTO.getFee(),paymentDTO.getRegisterFee(),paymentDTO.getTotalFee(),register));
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) {
        //return paymentDAO.update( new Payment(paymentDTO.getId(),paymentDTO.getFee(),paymentDTO.getRegisterFee(),paymentDTO.getTotalFee()));
    return true;
    }

    @Override
    public boolean delete(PaymentDTO paymentDTO) {
        //return paymentDAO.delete(new Payment(paymentDTO.getId(),paymentDTO.getFee(),paymentDTO.getRegisterFee(),paymentDTO.getTotalFee()));
        return true;
    }

    public boolean savePayment(Payment payment){
        return paymentDAO.save(payment);
    }

    @Override
    public PaymentDTO get(String value) {
        Payment object = paymentDAO.getObject(value);
       // return new PaymentDTO(object.getId(),);
        return null;
    }

    @Override
    public List<PaymentDTO> getAll() {
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        List<Payment> all = paymentDAO.getAll();
        for (Payment payment : all){
            paymentDTOS.add(new PaymentDTO(payment.getId(),payment.getFee(),payment.getRegisterFee(),payment.getTotalFee()));
        }
        return paymentDTOS;
    }
    @Override
    public List<String> getIds() {
        return paymentDAO.getIds();
    }

    @Override
    public String getCurrentId() {
        return paymentDAO.getCurrentId();
    }

}
