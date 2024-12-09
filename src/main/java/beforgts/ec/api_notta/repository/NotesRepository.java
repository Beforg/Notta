package beforgts.ec.api_notta.repository;

import beforgts.ec.api_notta.domain.notes.Notes;
import beforgts.ec.api_notta.domain.notes.NotesDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotesRepository extends MongoRepository<Notes, UUID> {
    Optional<Notes> findById(String  id);
    Optional<List<NotesDTO>> findByFavorite(boolean favorite);
    @Transactional
    @Query(value = "{ 'id' : :#{#id} }", delete = true)
    void deleteByIdCustom(@Param("id") String id);
}
