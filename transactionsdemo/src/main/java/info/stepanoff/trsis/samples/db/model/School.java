package info.stepanoff.trsis.samples.db.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "School")
@Data

public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    public School() {
    }

    public School(Integer id, Integer number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }
    public School(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    @Id
    @Column(name = "school_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @CreatedBy
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User createdUser;
    
    @LastModifiedBy 
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User modifiedUser;

    @OneToMany(targetEntity = Department.class, mappedBy = "school" , fetch = FetchType.LAZY)
    private List<Department> deptsList;

    @Column(name = "school_number", unique=true)
    private Integer number;

    @Column(name = "school_name")
    private String name;

}
