package info.stepanoff.trsis.samples.db.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "Dept")
@Data
public class Department implements Serializable {

    public Department() {
    }

    public Department(Integer school, String number, String name) {
        this.school = school;
        this.number = number;
        this.name = name;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Integer id;

    @Column(name = "dept_number")
    private String number;
    
    @Column(name = "dept_name")
    private String name;
    
    @Column(name = "dept_school_id")
    private Integer school;

}
