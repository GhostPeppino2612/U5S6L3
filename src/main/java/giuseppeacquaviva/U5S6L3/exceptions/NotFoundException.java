package giuseppeacquaviva.U5S6L3.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(long id) {
        super(id + " non trovato!");
    }
}