package info.stepanoff.trsis.samples.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.model.Department;
import info.stepanoff.trsis.samples.db.dao.DeptRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository batchRepository;

    @Override
    public Iterable<Department> findBySchool(Integer school) {
        return batchRepository.findBySchool(school);
    }

}
