/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.dao.SchoolRepository;
import info.stepanoff.trsis.samples.db.model.SchoolPE;
import info.stepanoff.trsis.samples.rest.model.SchoolDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    private final ObjectMapper objectMapper;

    @Override
    public List<SchoolDTO> listAll() {
        return schoolRepository.findAll().stream()
                .map(schoolPE -> objectMapper.convertValue(schoolPE, SchoolDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public SchoolDTO add(Integer number, String name) {
        return objectMapper.convertValue(schoolRepository.save(new SchoolPE(number, name)), SchoolDTO.class);
    }

    @Override
    public SchoolDTO findByNumber(Integer number) {
        var schoolPE = schoolRepository.findByNumber(number);
        return schoolPE.map(school -> objectMapper.convertValue(school, SchoolDTO.class)).orElse(null);
    }
}
