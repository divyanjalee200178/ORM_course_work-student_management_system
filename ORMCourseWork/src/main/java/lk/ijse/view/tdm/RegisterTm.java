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
    private String registerId;
    private String date;
    private String studentId; // Changed from Student object to String
    private String programId; // Changed from Program object to String
    private String studentName;
    private String programName;
    private double programFee;
    private double regiFee;

    public RegisterTm(String registerId, String date, String studentId, String programId,
                      String studentName, String programName, double programFee, double regiFee) {
        this.registerId = registerId;
        this.date = date;
        this.studentId = studentId; // Assign student ID
        this.programId = programId; // Assign program ID
        this.studentName = studentName;
        this.programName = programName;
        this.programFee = programFee;
        this.regiFee = regiFee;
    }

}
