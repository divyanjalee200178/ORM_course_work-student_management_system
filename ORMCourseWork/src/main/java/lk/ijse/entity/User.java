package lk.ijse.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String role;
    private String tel;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Student> students;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



    public User(String id, String name, String role, String tel, String email, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }

    public User() {
    }
}
