package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    private String id;
    private String studentId;
    private String programmeId;
    private double fee;
    private double registerFee;
    private double totalFee;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "programme_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Program programs;

    public Payment(String id, String studentId, String programmeId, double fee, double registerFee, double totalFee) {
        this.id = id;
        this.studentId = studentId;
        this.programmeId = programmeId;
        this.fee = fee;
        this.registerFee = registerFee;
        this.totalFee = totalFee;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", programmeId='" + programmeId + '\'' +
                ", fee=" + fee +
                ", registerFee=" + registerFee +
                ", totalFee=" + totalFee +
                '}';
    }
}
