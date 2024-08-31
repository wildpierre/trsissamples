/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Pavel Stepanov
 */
@Data
@Schema(description = "Факультет")
public class SchoolDTO {

    @Schema(description = "Идентификатор факультета")
    private Integer id;
    @Schema(description = "Номер факультета")
    private Integer number;
    @Schema(description = "Название факультета")
    private String name;
}
