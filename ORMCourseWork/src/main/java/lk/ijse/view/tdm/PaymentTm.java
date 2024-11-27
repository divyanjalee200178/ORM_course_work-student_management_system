package lk.ijse.view.tdm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.entity.Program;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentTm {

    private String id;
//    private String studentId;
//    private String programmeId;
    private String register_id;
    private double fee;
    private double registerFee;
    private double totalFee;



    public PaymentTm(String id, double fee, double registerFee, double totalFee) {
        this.id = id;
        this.fee = fee;
        this.registerFee = registerFee;
        this.totalFee = totalFee;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", fee=" + fee +
                ", registerFee=" + registerFee +
                ", totalFee=" + totalFee +
                '}';
    }
}
