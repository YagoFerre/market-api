package yago.ferreira.marketapi.infra.config.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Email já cadastrado");
    }
}
