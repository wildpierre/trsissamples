package info.stepanoff.trsis.samples.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "Batch")
public class Batch {

    public Batch() {
    }

    public Batch(Integer id, String nomer) {
        this.id = id;
        this.number = nomer;

    }

    @Id
    @Column(name = "batch_id")
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Column(name = "batch_number")
    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Column(name = "batch_school_id")
    private Integer school;

    public void setSchool(Integer school) {
        this.school = school;
    }

    public Integer getSchool() {
        return school;
    }

}
