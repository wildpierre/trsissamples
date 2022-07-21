/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import info.stepanoff.trsis.samples.service.SchoolService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.security.Principal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public/rest/schools")
@Api(value = "SchoolsAPI", description = "API for accessing schools")
public class SchoolRestService {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "", method = RequestMethod.GET)

    @ApiOperation(value = "View a list of all available schools", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(schoolService.listAll());
    }

    @ApiOperation(value = "Remove a school by ID", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully removed"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, Principal principal) {

        if (principal == null) {
            throw new ForbiddenException();
        }

        schoolService.delete(id);
    }

    @RequestMapping(value = "/mockdelete/{id}", method = RequestMethod.GET)
    public void mockdelete(@PathVariable("id") Integer id, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        schoolService.delete(id);
    }

    @RequestMapping(value = "/{number}/{name}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("number") Integer number, @PathVariable("name") String name, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        return ResponseEntity.ok(schoolService.add(number, name));
    }

    @ApiOperation(value = "Find a school by number", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully found"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByNumber(@PathVariable("number") Integer number) {
        return ResponseEntity.ok(schoolService.findByNumber(number));
    }

}
