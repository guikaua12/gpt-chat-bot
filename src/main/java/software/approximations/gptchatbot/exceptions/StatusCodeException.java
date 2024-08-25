package software.approximations.gptchatbot.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StatusCodeException extends RuntimeException {
    private final HttpStatus status;

    public StatusCodeException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
