package co.com.sofka.SpringBootApiMongoDB.SpringBootLibrary.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

}
