package ch05.util;

import java.io.Serial;

@SuppressWarnings("unused")
public class InvariantException extends RuntimeException {
    public InvariantException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
