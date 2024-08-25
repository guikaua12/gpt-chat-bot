package software.approximations.gptchatbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import software.approximations.gptchatbot.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u WHERE u.id = :id")
    Optional<User> findById(Long id);

    @Query("SELECT u from User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);
}
