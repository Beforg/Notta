package beforgts.ec.api_notta.domain.notes;

public record NotesDTO(
        String id,
        String title,
        String content,
        String creation,
        String lastUpdate,
        boolean favorite) {

    public NotesDTO(Notes notes) {
        this(
                notes.getId(),
                notes.getTitle(),
                notes.getContent(),
                notes.getCreation().toString(),
                (notes.getLastUpdate() != null) ? notes.getLastUpdate().toString() : "Sem atualizações",
                notes.isFavorite()
        );
    }
}
