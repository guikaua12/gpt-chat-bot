package software.approximations.gptchatbot.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name="users")
@EqualsAndHashCode(of="id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force=true)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final Long id;
    private String email;
    private String password;
    private final Instant createdAt;
}
