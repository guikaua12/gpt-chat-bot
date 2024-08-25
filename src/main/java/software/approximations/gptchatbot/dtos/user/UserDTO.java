package software.approximations.gptchatbot.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor(force=true)
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private Instant createdAt;
}
