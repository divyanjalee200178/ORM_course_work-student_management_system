package lk.ijse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class PaymentDTO {

    private String id;
    private String studentId;
    private String programmeId;
    private double fee;
    private double registerFee;
    private double totalFee;



    public PaymentDTO(String id, String studentId, String programmeId, double fee, double registerFee, double totalFee) {
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
