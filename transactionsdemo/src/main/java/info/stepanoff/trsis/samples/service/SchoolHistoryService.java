package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.SchoolHistory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public interface SchoolHistoryService {

    SchoolHistory add(Integer schoolId, Integer number, String name, String operation);
    
    SchoolHistory add(Integer schoolId, Integer oldNumber, Integer newNumber, String oldName, String newName, String operation);
    
    Iterable<SchoolHistory> listBySchoolId(Integer schoolId);
    
    Iterable<SchoolHistory> listAll();

}
