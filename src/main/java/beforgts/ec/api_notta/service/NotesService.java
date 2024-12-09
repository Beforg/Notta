package beforgts.ec.api_notta.service;

import beforgts.ec.api_notta.domain.notes.Notes;
import beforgts.ec.api_notta.domain.notes.CreateNotesDTO;
import beforgts.ec.api_notta.domain.notes.NotesDTO;
import beforgts.ec.api_notta.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotesService {

    @Autowired
    private NotesRepository repository;

    public void create(CreateNotesDTO dto) {
        Notes notes = new Notes(dto);
        this.repository.save(notes);
    }

    public List<NotesDTO> list() {
        return this.repository.findAll().stream().map(NotesDTO::new).toList();
    }

    public List<NotesDTO> listFavorite() {
        return this.repository.findByFavorite(true).isPresent() ? this.repository.findByFavorite(true).get() : List.of();
    }

    public void delete(String id) {
        this.repository.deleteByIdCustom(id);
    }

    public void update(NotesDTO dto) {
        Optional<Notes> notes = this.repository.findById(dto.id());
        System.out.println("ID: " + dto.id());
        if(notes.isPresent()) {
            System.out.println("Nota: " + notes.get().getTitle());
            notes.get().setTitle(dto.title());
            notes.get().setContent(dto.content());
            notes.get().setLastUpdate(LocalDate.now());
            notes.get().setFavorite(dto.favorite());
            this.repository.save(notes.get());
        }
    }
}
