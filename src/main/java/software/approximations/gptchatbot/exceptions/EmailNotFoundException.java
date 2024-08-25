package software.approximations.gptchatbot.exceptions;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends StatusCodeException {

    public EmailNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Email not found.");
    }
}
