package info.stepanoff.trsis.samples.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.dao.SchoolRepository;
import info.stepanoff.trsis.samples.db.model.School;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public Iterable<School> listAll() {
        return schoolRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public School add(Integer number, String name) {
        return schoolRepository.save(new School(number, name));
    }

    @Override
    public School findByNumber(Integer number) {
        return schoolRepository.findByNumber(number);
    }

}
