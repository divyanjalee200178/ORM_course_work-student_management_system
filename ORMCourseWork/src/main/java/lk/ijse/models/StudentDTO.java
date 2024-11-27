package lk.ijse.models;

import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO  {
    private String id;
    private User user;
    private String name;
    private String address;
    private String email;
    private String tel;

    public StudentDTO(String id, User user, String name) {
        this.id = id;
        this.user = user;
        this.name = name;
    }

    public StudentDTO(String id, String name, String address, String email, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
    }
}
