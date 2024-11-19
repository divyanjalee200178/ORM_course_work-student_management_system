package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Student;
import lk.ijse.models.PaymentDTO;
import lk.ijse.models.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOIml implements PaymentBO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public boolean save(PaymentDTO paymentDTO) {
        return paymentDAO.save(new Payment(paymentDTO.getId(),paymentDTO.getStudentId(),paymentDTO.getProgrammeId(),paymentDTO.getFee(),paymentDTO.getRegisterFee(),paymentDTO.getTotalFee()));
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) {
        return paymentDAO.update( new Payment(paymentDTO.getId(),paymentDTO.getStudentId(),paymentDTO.getProgrammeId(),paymentDTO.getFee(),paymentDTO.getRegisterFee(),paymentDTO.getTotalFee()));
    }

    @Override
    public boolean delete(PaymentDTO paymentDTO) {
        return paymentDAO.delete(new Payment(paymentDTO.getId(),paymentDTO.getStudentId(),paymentDTO.getProgrammeId(),paymentDTO.getFee(),paymentDTO.getRegisterFee(),paymentDTO.getTotalFee()));
    }

    @Override
    public PaymentDTO get(String value) {
        Payment object = paymentDAO.getObject(value);
        return new PaymentDTO(object.getId(),object.getStudentId(),object.getProgrammeId(),object.getFee(),object.getRegisterFee(),object.getTotalFee());
    }

    @Override
    public List<PaymentDTO> getAll() {
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        List<Payment> all = paymentDAO.getAll();
        for (Payment payment : all){
            paymentDTOS.add(new PaymentDTO(payment.getId(),payment.getStudentId(),payment.getProgrammeId(),payment.getFee(),payment.getRegisterFee(),payment.getTotalFee()));
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
