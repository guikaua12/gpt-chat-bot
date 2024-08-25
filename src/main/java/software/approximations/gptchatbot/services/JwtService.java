package software.approximations.gptchatbot.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import software.approximations.gptchatbot.dtos.auth.JwtPayload;
import software.approximations.gptchatbot.entities.User;
import software.approximations.gptchatbot.properties.JwtProperties;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService {
    public static final String ISSUER = "gpt-chat-bot";
    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;

    public JwtService(JwtProperties jwtProperties) {
        this.algorithm = Algorithm.HMAC256(jwtProperties.getSecret());

        this.jwtVerifier = JWT.require(this.algorithm)
                .withIssuer(ISSUER)
                .build();
    }

    public String createToken(User user) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(Instant.now().plus(3, ChronoUnit.DAYS))
                .sign(algorithm);
    }

    public JwtPayload parseToken(String token) {
        final DecodedJWT jwt = this.jwtVerifier.verify(token);

        final Long id = Long.parseLong(jwt.getSubject());
        final String email = jwt.getClaim("email").asString();

        return new JwtPayload(id, email);
    }

}
