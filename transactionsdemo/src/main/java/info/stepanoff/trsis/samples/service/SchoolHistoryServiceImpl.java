package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dao.SchoolHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.model.SchoolHistory;
import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Sort;

@Service
@Log4j
public class SchoolHistoryServiceImpl implements SchoolHistoryService {

    @Autowired
    private SchoolHistoryRepository schoolHistoryRepository;

    @Override
    public SchoolHistory add(Integer schoolId, Integer number, String name, String operation) {
        return schoolHistoryRepository.save(new SchoolHistory(schoolId, number, name, operation));
    }

    @Override
    public SchoolHistory add(Integer schoolId, Integer oldNumber, Integer newNumber, String oldName, String newName, String operation) {
        return schoolHistoryRepository.save(new SchoolHistory(schoolId, oldNumber, newNumber, oldName, newName, operation));
    }

    @Override
    public Iterable<SchoolHistory> listBySchoolId(Integer schoolId) {
        return schoolHistoryRepository.findBySchoolId(schoolId);
    }

    @Override
    public Iterable<SchoolHistory> listAll() {
        return schoolHistoryRepository.findAll(new Sort(Sort.Direction.DESC, "changeDate"));
    }
    
}
