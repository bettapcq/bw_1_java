package team5.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("l'id: " + id+ " non è stato trovato");
    }
}
