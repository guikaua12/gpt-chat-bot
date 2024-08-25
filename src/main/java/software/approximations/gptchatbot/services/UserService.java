package software.approximations.gptchatbot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.approximations.gptchatbot.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

   
}
