package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.models.RegisterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Register {

    @Id
    private String register_id;
    private String date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    private String studentName;
    private String programName;
    private double programFee;
    private double regiFee;

    public Register(String register_id, String date, String studentName, String programName, double programFee, double regiFee) {
        this.register_id = register_id;
        this.date = date;
        this.studentName = studentName;
        this.programName = programName;
        this.programFee = programFee;
        this.regiFee = regiFee;
    }

    public Register(String register_id) {
        this.register_id = register_id;


    }




    @Override
    public String toString() {
        return "Register{" +
                "register_id='" + register_id + '\'' +
                ", date='" + date + '\'' +
                ", studentName='" + studentName + '\'' +
                ", programName='" + programName + '\'' +
                ", programFee=" + programFee +
                ", regiFee=" + regiFee +
                '}';
    }
}
