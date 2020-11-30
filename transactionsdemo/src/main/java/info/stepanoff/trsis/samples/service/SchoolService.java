package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.School;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public interface SchoolService {

    Iterable<School> listAll();

    void delete(Integer id);
    
    School findById(Integer id);
    
    School findByNumber(Integer id);
    
    School add(Integer number, String name);

}
