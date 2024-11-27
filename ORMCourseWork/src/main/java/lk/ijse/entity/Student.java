package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private String tel;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Register> registerList;


    public Student(String id, String name, String address, String email, String tel, User user) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.user = user;
    }

    public Student(String id,String name, String address, String email, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;

    }


    public Student(String id, User user, String name, String address, String tel, String email) {
        this.id = id;
        this.user=user;
        this.name=name;
        this.address = address;
        this.tel=tel;
        this.email=email;
    }
}
