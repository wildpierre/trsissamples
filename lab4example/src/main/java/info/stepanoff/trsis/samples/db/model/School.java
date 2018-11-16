/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.db.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "SCHOOL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    public School(Integer nomer, String name) {
        this.number = nomer;
        this.name = name;
    }

    @Id
    @Column(name = "SCHOOL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = Batch.class, mappedBy = "school", fetch = FetchType.LAZY)
    private List<Batch> batchesList;

    @Column(name = "SCHOOL_NUMBER", unique = true)
    private Integer number;

    @Column(name = "SCHOOL_NAME")
    private String name;
}
