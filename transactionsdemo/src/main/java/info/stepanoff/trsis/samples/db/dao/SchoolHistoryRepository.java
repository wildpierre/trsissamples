package info.stepanoff.trsis.samples.db.dao;

import info.stepanoff.trsis.samples.db.model.SchoolHistory;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pavel
 */
@Transactional(readOnly = true)
public interface SchoolHistoryRepository extends PagingAndSortingRepository<SchoolHistory, Integer> {             
    
         List<SchoolHistory> findById(Integer id);
                
         List<SchoolHistory> findBySchoolId(Integer schoolid);
}
