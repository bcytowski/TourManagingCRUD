package pl.sda.javagda28.tourmanagingcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.javagda28.tourmanagingcrud.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
