/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.dao;

import info.stepanoff.trsis.samples.db.model.SchoolPE;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pavel
 */
public interface SchoolRepository extends CrudRepository<SchoolPE, Integer> {

    public Optional<SchoolPE> findByNumber(Integer number);

    public List<SchoolPE> findAll();
}
