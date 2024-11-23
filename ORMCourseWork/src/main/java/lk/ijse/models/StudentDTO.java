package lk.ijse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO  {
    private String id;
    private String name;
    private String address;
    private String email;
    private String tel;
    private double payment;
    private String userId;


}
