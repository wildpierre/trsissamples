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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public/rest/schools")
@RequiredArgsConstructor
public class SchoolRestController {

    private final SchoolService schoolService;

    @RequestMapping(value = "", method = RequestMethod.GET)

    @Operation(summary = "Получить перечень факультетов",
            description = "Получить перечень факультетов - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    public ResponseEntity<List<SchoolDTO>> browse() {
        return ResponseEntity.ok(schoolService.listAll());
    }

    @Operation(summary = "Удаление факультета",
            description = "Факультет может быть удален, если с ним не связаны группы",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")
            @Parameter(description = "Идентификатор факультета") Integer id) {
        schoolService.delete(id);
    }

    @Operation(summary = "Создать новый факультет",
            description = "Создать новый факультет - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{number}/{name}", method = RequestMethod.POST)
    public ResponseEntity<SchoolDTO> add(
            @PathVariable("number")
            @Parameter(description = "Номер факультета") Integer number,
            @PathVariable("name")
            @Parameter(description = "Название факультета") String name) {
        return ResponseEntity.ok(schoolService.add(number, name));
    }

    @Operation(summary = "Поиск факультета по номеру",
            description = "Поиск факультета по номеру - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public ResponseEntity<SchoolDTO> findByNumber(@PathVariable("number")
            @Parameter(description = "Номер факультета") Integer number) {
        return ResponseEntity.ok(schoolService.findByNumber(number));
    }
}
