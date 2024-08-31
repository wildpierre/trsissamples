/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Pavel Stepanov
 */
@Entity
@Table(name = "SCHOOL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolPE implements Serializable {

    private static final long serialVersionUID = 1L;

    public SchoolPE(Integer nomer, String name) {
        this.number = nomer;
        this.name = name;
    }

    @Id
    @Column(name = "SCHOOL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = BatchPE.class, mappedBy = "school", fetch = FetchType.LAZY)
    private List<BatchPE> batchesList;

    @Column(name = "SCHOOL_NUMBER", unique = true)
    private Integer number;

    @Column(name = "SCHOOL_NAME")
    private String name;
}
