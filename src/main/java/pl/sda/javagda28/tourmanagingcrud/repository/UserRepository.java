package pl.sda.javagda28.tourmanagingcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import pl.sda.javagda28.tourmanagingcrud.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Long countAllByUsernameOrEmail(@NonNull String username, @NonNull String email);

    @Query("SELECT u FROM users u left join fetch u.roles WHERE u.username = :username")
    Optional<User> findByUsernameWithRoles(@Param("username")String username);
}
