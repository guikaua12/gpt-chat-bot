package software.approximations.gptchatbot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.approximations.gptchatbot.dtos.auth.LoginParams;
import software.approximations.gptchatbot.dtos.auth.LoginResponse;
import software.approximations.gptchatbot.dtos.auth.RegisterParams;
import software.approximations.gptchatbot.dtos.auth.RegisterResponse;
import software.approximations.gptchatbot.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginParams params) {
        return this.authService.login(params);
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterParams params) {
        return this.authService.register(params);
    }
}
