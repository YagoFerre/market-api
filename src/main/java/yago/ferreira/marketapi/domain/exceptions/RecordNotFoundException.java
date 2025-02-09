package yago.ferreira.marketapi.domain.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
        super("Registro não encontrado com o id: " + id);
    }
}
