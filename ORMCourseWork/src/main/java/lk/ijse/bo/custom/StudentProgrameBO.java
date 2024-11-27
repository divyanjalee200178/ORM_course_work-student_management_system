package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.entity.Register;
import lk.ijse.models.PaymentDTO;
import lk.ijse.models.ProgramDTO;
import lk.ijse.models.RegisterDTO;
import lk.ijse.models.StudentDTO;
import lk.ijse.view.tdm.CartTm;

import java.util.List;

public interface StudentProgrameBO extends SuperBO {
    String getCurrentId();

    public StudentDTO searchStudent(String studentId);
    public List<RegisterDTO> getAllRegistrations();

    public boolean saveRegistration(RegisterDTO registrationDTO);

    public ProgramDTO searchProgram(String programId);

    List<RegisterDTO> getAll();
//    boolean save(CartTm cartTm);
//
//    void placeOfRegister(List<RegisterDTO> registrationDTOList);
//
//    boolean placeRegister(List<RegisterDTO> registerDTO);
//    void placeRegisters(List<RegisterDTO> registrationDTOList);
//
//    void saveRegisters(List<RegisterDTO> registrationDTOList);

}
