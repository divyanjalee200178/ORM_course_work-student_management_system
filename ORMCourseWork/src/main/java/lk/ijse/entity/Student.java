package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
    private double payment;
    private String userId;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "studentProgramme",
            joinColumns = @JoinColumn(name = "s_id"),
            inverseJoinColumns = @JoinColumn(name = "p_code")
    )
    private Set<Program> programs = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Payment> payments = new HashSet<>();

    public Student(String id, String name, String address, String email, String tel, double payment,String userId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.payment = payment;
        this.userId=userId;

    }
}
