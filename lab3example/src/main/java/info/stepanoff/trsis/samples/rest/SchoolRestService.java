package info.stepanoff.trsis.samples.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import info.stepanoff.trsis.samples.service.SchoolService;

@RestController
@RequestMapping("/public/rest/schools")
public class SchoolRestService {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(schoolService.listAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        schoolService.delete(id);
    }

    @RequestMapping(value = "/{number}/{name}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("number") Integer number, @PathVariable("name") String name) {
        return ResponseEntity.ok(schoolService.add(number, name));
    }
    
    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByNumber(@PathVariable("number") Integer number) {
        return ResponseEntity.ok(schoolService.findByNumber(number));
    }
}
