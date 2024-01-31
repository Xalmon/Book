package services;

public class CustomException extends RuntimeException {
    public CustomException(String bookNotFound) {
        super(bookNotFound);
    }
}
