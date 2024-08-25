package software.approximations.gptchatbot.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyInUse extends StatusCodeException {
    public EmailAlreadyInUse() {
        super(HttpStatus.CONFLICT, "Email already exists");
    }
}
