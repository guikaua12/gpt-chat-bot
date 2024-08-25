package software.approximations.gptchatbot.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import software.approximations.gptchatbot.repositories.UserRepository;
import software.approximations.gptchatbot.security.filter.JwtAuthenticationFilter;
import software.approximations.gptchatbot.services.JwtService;

@Configuration
@RequiredArgsConstructor
public class JwtConfigurer implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void init(HttpSecurity builder) throws Exception {

    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(new JwtAuthenticationFilter(jwtService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }
}
