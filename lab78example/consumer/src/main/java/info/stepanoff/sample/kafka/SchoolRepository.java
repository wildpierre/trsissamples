/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.sample.kafka;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pavel
 */
public interface SchoolRepository extends CrudRepository<School, Integer> {

}
