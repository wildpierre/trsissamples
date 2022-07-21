package info.stepanoff.trsis.samples.db.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "UserData")
@Data

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String passwordHash;


}
