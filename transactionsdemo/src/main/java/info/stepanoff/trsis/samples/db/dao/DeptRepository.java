package info.stepanoff.trsis.samples.db.dao;

import info.stepanoff.trsis.samples.db.model.Department;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pavel
 */
public interface DeptRepository extends CrudRepository<Department, Integer> {

     List<Department> findBySchool(Integer school);
}
