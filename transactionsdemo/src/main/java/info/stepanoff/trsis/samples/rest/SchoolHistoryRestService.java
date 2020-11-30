package info.stepanoff.trsis.samples.rest;

import info.stepanoff.trsis.samples.db.dto.SchoolHistoryDTO;
import info.stepanoff.trsis.samples.service.SchoolHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/")
public class SchoolHistoryRestService {

    @Autowired
    private SchoolHistoryService schoolHistoryService;
  
    private final Long defaultLength = 10L;

    @RequestMapping(value = "/public/rest/history/schools/{school}", method = RequestMethod.GET)
    public ResponseEntity<Object> browse(@PathVariable("school") Integer schoolId, long length) {
        return ResponseEntity.ok(StreamSupport.stream(schoolHistoryService.listBySchoolId(schoolId).spliterator(), false).limit(length).map(x -> new SchoolHistoryDTO(x)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/public/rest/history/schools/", method = RequestMethod.GET)
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(StreamSupport.stream(schoolHistoryService.listAll().spliterator(), false).limit(defaultLength).map(x -> new SchoolHistoryDTO(x)).collect(Collectors.toList()));
    }
    
}
