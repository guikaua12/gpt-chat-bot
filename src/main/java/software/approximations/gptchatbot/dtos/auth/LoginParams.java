package software.approximations.gptchatbot.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginParams(@NotEmpty @Email String email, @NotEmpty String password) {
}
