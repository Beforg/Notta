package beforgts.ec.api_notta.domain.notes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notes {
    @Id
    private String id = UUID.randomUUID().toString();
    private String title;
    private String content;
    private LocalDate creation;
    private LocalDate lastUpdate;
    private boolean favorite;

    public Notes(CreateNotesDTO dto) {
        this.title = dto.title();
        this.content = dto.content();
        this.creation = dto.creation();
        this.lastUpdate = dto.lastUpdate();
        this.favorite = dto.favorite();
    }
}
