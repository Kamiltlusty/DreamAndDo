package pl.kamil.dreamanddoapi.domain.exceptions;

public class DreamNotFoundException extends RuntimeException {
    public DreamNotFoundException(String message) {
        super(message);
    }
}
