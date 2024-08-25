package software.approximations.gptchatbot.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.approximations.gptchatbot.dtos.user.UserDTO;
import software.approximations.gptchatbot.entities.User;
import software.approximations.gptchatbot.security.details.JwtUserDetails;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final ModelMapper modelMapper;

    @GetMapping("/me")
    public UserDTO me(@AuthenticationPrincipal JwtUserDetails userDetails) {
        return modelMapper.map(userDetails.getUser(), UserDTO.class);
    }
}
