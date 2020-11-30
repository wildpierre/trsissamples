package info.stepanoff.trsis.samples.db.dto;

import info.stepanoff.trsis.samples.db.model.School;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Pavel
 */
@Data

public class SchoolDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public SchoolDTO(School school) {
        this.id = school.getId();
        this.deptsCount = school.getDeptsList().size();
        this.name = school.getName();
        this.number = school.getNumber();
    }
    
    private Integer id;
    private Integer deptsCount;
    private Integer number;
    private String name;

}
