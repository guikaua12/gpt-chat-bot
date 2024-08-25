package software.approximations.gptchatbot.security.details;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import software.approximations.gptchatbot.entities.User;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class JwtUserDetails implements UserDetails {
    private final User user;

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
