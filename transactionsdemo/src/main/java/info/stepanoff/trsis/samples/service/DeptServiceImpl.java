package info.stepanoff.trsis.samples.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.model.Department;
import info.stepanoff.trsis.samples.db.dao.DeptRepository;

@Service
public class DeptServiceImpl implements DeptService {

    private final static Logger log = Logger.getLogger(SchoolServiceImpl.class);

    @Autowired
    private DeptRepository batchRepository;

    @Override
    public Iterable<Department> findBySchool(Integer school) {
        return batchRepository.findBySchool(school);
    }

}
