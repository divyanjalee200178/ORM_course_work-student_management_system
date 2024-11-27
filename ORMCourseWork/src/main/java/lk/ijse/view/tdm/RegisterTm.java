package lk.ijse.view.tdm;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.entity.Program;
import lk.ijse.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegisterTm {
    private String register_id;
    private String date;
    private String student_id;
    private String program_id;
    private String studentName;
    private String programName;
    private double programFee;
    private double regiFee;

    public RegisterTm(String register_id, String date, String student_id, String program_id, String studentName, String programName, double programFee, double regiFee) {
        this.register_id = register_id;
        this.date = date;
        this.student_id = student_id;
        this.program_id = program_id;
        this.studentName = studentName;
        this.programName = programName;
        this.programFee = programFee;
        this.regiFee = regiFee;
    }
}
