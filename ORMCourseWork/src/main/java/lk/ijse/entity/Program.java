package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Program {
    @Id
    private String code;
    private String name;
    private double price;
    private int duration;

    @OneToMany(mappedBy = "programs")
    private Set<Payment> payments = new HashSet<>();

    @ManyToMany(mappedBy = "programs")
    private Set<Student> students = new HashSet<>();
    public Program(String code, String name, double price, int duration) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.duration = duration;
    }
}
