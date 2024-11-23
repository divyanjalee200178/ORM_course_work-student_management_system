package lk.ijse.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentTm {
    private String id;
    private String name;
    private String address;
    private String email;
    private String tel;
    private double payment;
    private String userId;


}
