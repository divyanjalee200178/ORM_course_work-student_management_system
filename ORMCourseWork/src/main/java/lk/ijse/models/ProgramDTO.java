package lk.ijse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProgramDTO {
    @Id
    private String code;
    private String name;
    private double price;
    private int duration;
}
