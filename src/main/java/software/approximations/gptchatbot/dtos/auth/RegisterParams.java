package software.approximations.gptchatbot.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegisterParams(@NotEmpty @Email String email, @NotEmpty String password) {
}
