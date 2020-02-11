package pl.sda.javagda28.tourmanagingcrud.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "roles")
public class Role {

    @Id
    private String name;
}
