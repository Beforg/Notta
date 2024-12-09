package beforgts.ec.api_notta.domain.notes;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateNotesDTO(
        String title,
        String content,
        LocalDate creation,
        LocalDate lastUpdate,
        boolean favorite) {
}
