package beforgts.ec.api_notta.controller;

import beforgts.ec.api_notta.domain.notes.CreateNotesDTO;
import beforgts.ec.api_notta.domain.notes.NotesDTO;
import beforgts.ec.api_notta.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController { // controller para as notas com crud básico

    @Autowired
    private NotesService service;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateNotesDTO dto){
        this.service.create(dto);
        return ResponseEntity.ok("Nota criada com sucesso.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<NotesDTO>> listAll(){
        return ResponseEntity.ok(this.service.list());
    }

    @GetMapping("/list/favorites")
    public ResponseEntity<List<NotesDTO>> listFavorites(){
        return ResponseEntity.ok(this.service.listFavorite());
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody NotesDTO dto){
        this.service.update(dto);
        return ResponseEntity.status(200).body("Nota atualizada com sucesso.");
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable String id){
        this.service.delete(id);
        return ResponseEntity.status(204).body("Nota deletada com sucesso.");
    }
}
