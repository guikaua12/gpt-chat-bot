package software.approximations.gptchatbot.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import software.approximations.gptchatbot.dtos.auth.JwtPayload;
import software.approximations.gptchatbot.entities.User;
import software.approximations.gptchatbot.repositories.UserRepository;
import software.approximations.gptchatbot.security.details.JwtUserDetails;
import software.approximations.gptchatbot.security.token.JwtAuthenticationToken;
import software.approximations.gptchatbot.services.JwtService;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token == null || token.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.replace("Bearer ", "");

        try {
            final JwtPayload payload = jwtService.parseToken(token);

            final Optional<User> userOptional = userRepository.findById(payload.id());

            if (userOptional.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            final User user = userOptional.get();
            final JwtUserDetails userDetails = new JwtUserDetails(user);

            final Authentication authentication = new JwtAuthenticationToken(userDetails, true);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (JWTVerificationException exception) {
            filterChain.doFilter(request, response);
        }
    }
}
