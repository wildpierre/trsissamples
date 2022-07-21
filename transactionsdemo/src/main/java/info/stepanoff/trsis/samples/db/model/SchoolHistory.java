package info.stepanoff.trsis.samples.db.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "School_History")
@Data
@EntityListeners(AuditingEntityListener.class)
public class SchoolHistory implements Serializable  {

    private static final long serialVersionUID = 1L;

    public SchoolHistory() {
    }

    public SchoolHistory(Integer schoolId, Integer newNumber, String newName, String operation) {
        this.schoolId = schoolId;        
        this.newNumber = newNumber;
        this.newName = newName;
        this.operation = operation;
    }

    public SchoolHistory(Integer schoolId, Integer oldNumber, Integer newNumber, String oldName, String newName, String operation) {
        this(schoolId, newNumber, newName, operation);
        this.oldNumber = oldNumber;
        this.oldName = oldName;
    }

    public SchoolHistory(Integer id, Integer schoolId, Integer newNumber, String newName, String operation) {
        this(schoolId, newNumber, newName, operation);
        this.id = id;
    }

    public SchoolHistory(Integer id, Integer schoolId, Integer oldNumber, Integer newNumber, String oldName, String newName, String operation) {
        this(schoolId, oldNumber, newNumber, oldName, newName, operation);
        this.id = id;
    }

    @Id
    @Column(name = "school_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "school_history_user_name")
    @CreatedBy
    private String user;

    @Column(name = "school_history_school_id")
    private Integer schoolId;

    @Column(name = "school_history_school_new_number")
    private Integer newNumber;

    @Column(name = "school_history_school_old_number")
    private Integer oldNumber;

    @Column(name = "school_history_school_new_name")
    private String newName;

    @Column(name = "school_history_school_old_name")
    private String oldName;

    @Column(name = "school_history_operation")
    private String operation;
    
    @CreatedDate
    @Column(name = "school_history_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date changeDate;

}
