package lk.ijse.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.bo.SuperBO;
import lk.ijse.bo.custom.StudentProgrameBO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dao.custom.StudentProgrameDAO;
import lk.ijse.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.dao.custom.impl.StudentProgramDAOImpl;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Program;
import lk.ijse.entity.Register;
import lk.ijse.entity.Student;
import lk.ijse.models.PaymentDTO;
import lk.ijse.models.ProgramDTO;
import lk.ijse.models.RegisterDTO;
import lk.ijse.models.StudentDTO;
import lk.ijse.view.tdm.CartTm;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentProgrammeBOIml implements StudentProgrameBO {
    StudentProgrameDAO studentProgramDAO = (StudentProgrameDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENTPROGRAM);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAMME);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);


    //private StudentProgrameDAO studentProgrameDAO;


    private StudentProgrameDAO studentProgramDAO1;
    private PaymentDAO paymentDAO1;

    public StudentProgrammeBOIml() {
        // Initialize DAOs (normally injected via a service or factory)
        this.studentProgramDAO1 = new StudentProgramDAOImpl();
        this.paymentDAO1 = new PaymentDAOImpl();
    }

//    @Override
//    public List<RegisterDTO> getAll() {
//        /*List<RegisterDTO> registerDTOS = new ArrayList<>();
//        List<Register> all = studentProgramDAO.getAll();
//        for (Register register : all){
//            registerDTOS.add(new RegisterDTO(register.getRegister_id(),register.getDate(),register.getStudentName(),register.getProgramName(),register.getProgramFee(),register.getRegiFee()));
//        }
//        return registerDTOS;*/
//        List<RegisterDTO> regList = new ArrayList<>();
//        List<Register> registrations = studentProgramDAO.getAll();
////
////
////        for (Register registration : registrations){
////
////            Program programDTO = new Program(registration.getProgram().getCode());
////            Student studentDTO = new Student(registration.getStudent().getId());
////
////          /*  System.out.println(programDTO);
////*/
////            RegisterDTO registrationDTO = new RegisterDTO(registration.getRegister_id(),registration.getDate(),studentDTO,programDTO,registration.getStudentName(),registration.getProgramName(),registration.getProgramFee(),registration.getRegiFee());
////            regList.add(registrationDTO);
////        }
////        return regList;
////    }
//
//    }

    @Override
    public List<RegisterDTO> getAll() {
        List<RegisterDTO> registerDTOList = new ArrayList<>();
        List<Register> registerList = studentProgramDAO.getAll(); // Fetch data from DAO

        // Map Register entities to RegisterDTO
        for (Register register : registerList) {
            RegisterDTO registerDTO = new RegisterDTO(
                    register.getRegister_id(),
                    register.getDate(),
                    register.getStudent(),
                    register.getProgram(),
                    register.getStudentName(),
                    register.getProgramName(),
                    register.getProgramFee(),
                    register.getRegiFee()
            );
            registerDTOList.add(registerDTO);
        }

        return registerDTOList; // Return DTO list
    }


    private String generateRegisterId() {
        return UUID.randomUUID().toString();  // Example of generating a UUID
    }

    @Override
    public StudentDTO searchStudent(String studentId) {
        Student student = studentDAO.search(studentId);
        return new StudentDTO(student.getId(),student.getUser(),student.getName(),student.getAddress(),student.getEmail(),student.getTel());
    }

    @Override
    public ProgramDTO searchProgram(String programId) {
        Program program = programDAO.search(programId);

        return new ProgramDTO(program.getCode(),program.getName(),program.getPrice(),program.getDuration());
    }


    @Override
    public boolean saveRegistration(RegisterDTO registrationDTO) {

        Program programDTO =registrationDTO.getProgram();
        Program program = new Program(programDTO.getCode());


        Student studedntDto = registrationDTO.getStudent();
        Student student = new Student(studedntDto.getId());

        return studentProgramDAO.save(new Register(registrationDTO.getRegister_id(),registrationDTO.getDate(),student,program,registrationDTO.getStudentName(),registrationDTO.getProgramName(),registrationDTO.getProgramFee(),registrationDTO.getRegiFee()));
    }
    @Override
    public List<RegisterDTO> getAllRegistrations() {
                return null;
    }
    @Override
    public String getCurrentId() {
        return studentProgramDAO.getCurrentId();
    }














}


