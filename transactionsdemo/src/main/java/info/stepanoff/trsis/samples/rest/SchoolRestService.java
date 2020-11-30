package info.stepanoff.trsis.samples.rest;

import info.stepanoff.trsis.samples.db.dto.SchoolDTO;
import info.stepanoff.trsis.samples.db.model.School;
import info.stepanoff.trsis.samples.security.NeedRole;
import info.stepanoff.trsis.samples.service.SchoolHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import info.stepanoff.trsis.samples.service.SchoolService;
import java.security.Principal;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@RequestMapping("/")
@SessionAttributes("context")
public class SchoolRestService {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private SchoolHistoryService schoolHistoryService;

    private final static String DELETE = "DELETE";
    private final static String ADD = "ADD";

    @RequestMapping(value = "/public/rest/schools", method = RequestMethod.GET)
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(StreamSupport.stream(schoolService.listAll().spliterator(), false).map(x -> new SchoolDTO(x)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/public/rest/schools/{number}", method = RequestMethod.GET)
    public ResponseEntity<Object> findOne(@PathVariable("number") Integer number) {
        School school = schoolService.findByNumber(number);
        if (school == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new SchoolDTO(school));
        }

    }

    @Transactional
    @NeedRole(roleRegex = "ALL")
    @RequestMapping(value = "/rest/schools/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, @AuthenticationPrincipal Principal principal) {
        School school = schoolService.findById(id);       
        schoolHistoryService.add(school.getId(), school.getNumber(), null, school.getName(), null, DELETE);        
        schoolService.delete(id);
    }

    @Transactional
    @NeedRole(roleRegex = "ALL")
    @RequestMapping(value = "/rest/schools/{number}/{name}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("number") Integer number, @PathVariable("name") String name, @AuthenticationPrincipal Principal principal) {
        School school = schoolService.add(number, name);
        schoolHistoryService.add(school.getId(), school.getNumber(), school.getName(), ADD);
        return ResponseEntity.ok(school);
    }

}
