package pl.kamil.dreamanddoapi.domain.exceptions;

public class DreamAlreadyExistsException extends RuntimeException {
    public DreamAlreadyExistsException(final String title) {
        super(title);
    }
}
