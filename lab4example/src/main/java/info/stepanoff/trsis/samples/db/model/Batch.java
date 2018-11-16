/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "Batch")
@Data
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

    @Column(name = "batch_number")
    private String number;

    @Column(name = "batch_school_id")
    private Integer school;
}
