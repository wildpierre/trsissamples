package info.stepanoff.trsis.samples.db.dao;

import info.stepanoff.trsis.samples.db.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pavel
 */
@Transactional(readOnly = true)
public interface SchoolRepository extends CrudRepository<School, Integer> {

    School findByNumber(Integer number);
}
