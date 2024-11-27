package lk.ijse.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.entity.Program;
import lk.ijse.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class RegisterDTO {
    private String register_id;
    private String date;
    private Student student;
    private Program program;
    private String studentName;
    private String programName;
    private double programFee;
    private double regiFee;

    public RegisterDTO(String register_id, String date, Student student, Program program, String studentName, String programName, double programFee, double regiFee) {
        this.register_id = register_id;
        this.date = date;
        this.student = student;
        this.program = program;
        this.studentName = studentName;
        this.programName = programName;
        this.programFee = programFee;
        this.regiFee = regiFee;
    }

    public RegisterDTO(String register_id) {
        this.register_id = register_id;
    }

    public RegisterDTO(String registerId, String date, String studentName, String programName, double programFee, double regiFee) {
        this.register_id=registerId;
        this.date=date;
        this.studentName=studentName;
        this.programName=programName;
        this.programFee=programFee;
        this.regiFee=regiFee;
    }
}
