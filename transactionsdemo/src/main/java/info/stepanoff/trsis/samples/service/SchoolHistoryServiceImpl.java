package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dao.SchoolHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.model.SchoolHistory;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;

@Service
@Slf4j
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
        return schoolHistoryRepository.findAll(Sort.by(Sort.Direction.DESC,"changeDate").descending());
    }
    
}
