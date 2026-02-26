package pl.kamil.dreamanddoapi.domain.exceptions;

public class MissingDreamException extends RuntimeException {
    public MissingDreamException(String message) {
        super(message);
    }
}
