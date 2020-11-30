package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.Department;

public interface DeptService {

    Iterable<Department> findBySchool(Integer school);

}
