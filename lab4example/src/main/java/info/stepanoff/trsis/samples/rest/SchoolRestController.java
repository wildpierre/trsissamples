/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest;

import info.stepanoff.trsis.samples.rest.model.SchoolDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import info.stepanoff.trsis.samples.service.SchoolService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public/rest/schools")
@RequiredArgsConstructor
@Api(value = "SchoolsAPI", description = "API for accessing schools")
public class SchoolRestController {

    private final SchoolService schoolService;

    @RequestMapping(value = "", method = RequestMethod.GET)

    @Operation(summary = "Получить перечень факультетов",
            description = "Получить перечень факультетов методом GET",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    public ResponseEntity<List<SchoolDTO>> browse() {
        return ResponseEntity.ok(schoolService.listAll());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)

    @Operation(summary = "Получить перечень факультетов",
            description = "Получить перечень факультетов методом POST",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    public ResponseEntity<List<SchoolDTO>> browsePost(Principal principal) {
        return ResponseEntity.ok(schoolService.listAll());
    }

    @Operation(summary = "Удаление факультета",
            description = "Факультет может быть удален, если с ним не связаны группы",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, Principal principal) {

        if (principal == null) {
            throw new ForbiddenException();
        }

        schoolService.delete(id);
    }

    @Operation(summary = "Удаление факультета - метод для тестирования",
            description = "Факультет может быть удален, если с ним не связаны группы")
    @RequestMapping(value = "/mockdelete/{id}", method = RequestMethod.GET)
    public void mockdelete(@PathVariable("id") Integer id, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        schoolService.delete(id);
    }

    @RequestMapping(value = "/{number}/{name}", method = RequestMethod.POST)
    @Operation(summary = "Создать новый факультет",
            description = "Создать новый факультет - расширенное описание",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    public ResponseEntity<SchoolDTO> add(@PathVariable("number") Integer number, @PathVariable("name") String name, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        return ResponseEntity.ok(schoolService.add(number, name));
    }

    @Operation(summary = "Поиск факультета по номеру",
            description = "Поиск факультета по номеру - расширенное описание",
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public ResponseEntity<SchoolDTO> findByNumber(@PathVariable("number") Integer number) {
        return ResponseEntity.ok(schoolService.findByNumber(number));
    }

}
