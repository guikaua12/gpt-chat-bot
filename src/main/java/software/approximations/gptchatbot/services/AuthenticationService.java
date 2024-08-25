package software.approximations.gptchatbot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import software.approximations.gptchatbot.dtos.auth.LoginParams;
import software.approximations.gptchatbot.dtos.auth.LoginResponse;
import software.approximations.gptchatbot.dtos.auth.RegisterParams;
import software.approximations.gptchatbot.dtos.auth.RegisterResponse;
import software.approximations.gptchatbot.entities.User;
import software.approximations.gptchatbot.exceptions.EmailAlreadyInUse;
import software.approximations.gptchatbot.exceptions.EmailNotFoundException;
import software.approximations.gptchatbot.repositories.UserRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginParams params) {
        final User user = userRepository.findByEmail(params.email()).orElseThrow(EmailNotFoundException::new);

        if (!passwordEncoder.matches(params.password(), user.getPassword())) {
            throw new BadCredentialsException("Password does not match.");
        }

        return new LoginResponse(jwtService.createToken(user));
    }

    public RegisterResponse register(RegisterParams params) {
        userRepository.findByEmail(params.email()).ifPresent((user) -> {
            throw new EmailAlreadyInUse();
        });

        final String hashPassword = passwordEncoder.encode(params.password());

        final User user = new User(null, params.email(), hashPassword, Instant.now());

        userRepository.save(user);

        return new RegisterResponse(jwtService.createToken(user));
    }
}
