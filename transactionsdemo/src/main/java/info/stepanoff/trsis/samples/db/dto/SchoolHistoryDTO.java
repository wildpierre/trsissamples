package info.stepanoff.trsis.samples.db.dto;

import info.stepanoff.trsis.samples.db.model.SchoolHistory;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Pavel
 */
@Data

public class SchoolHistoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public SchoolHistoryDTO(SchoolHistory schoolHistory) {
        this.id = schoolHistory.getId();
        this.schoolId = schoolHistory.getSchoolId();
        
        this.newName = schoolHistory.getNewName();
        this.newNumber = schoolHistory.getNewNumber();
        
        this.oldName = schoolHistory.getOldName();
        this.oldNumber = schoolHistory.getOldNumber();
        
        this.userName = schoolHistory.getUser();       
        this.operation = schoolHistory.getOperation();
        
        this.date = schoolHistory.getChangeDate();
    }
    
    private Integer id;
    private Integer schoolId;
    
    
    private Integer oldNumber;
    private String oldName;
    private Integer newNumber;
    private String newName;
    
    private String userName;
    private String operation;
    
    private Date date;

}
