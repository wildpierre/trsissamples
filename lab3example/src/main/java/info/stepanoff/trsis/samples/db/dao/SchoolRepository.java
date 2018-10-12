package info.stepanoff.trsis.samples.db.dao;

import info.stepanoff.trsis.samples.db.model.School;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pavel
 */
public interface SchoolRepository extends CrudRepository<School, Integer> {

     public School findByNumber(Integer number);
}
