package software.approximations.gptchatbot.security.token;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import software.approximations.gptchatbot.security.details.JwtUserDetails;

import java.util.Collection;

@AllArgsConstructor
public class JwtAuthenticationToken implements Authentication {
    private final JwtUserDetails userDetails;
    private boolean authenticated;

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }
}
