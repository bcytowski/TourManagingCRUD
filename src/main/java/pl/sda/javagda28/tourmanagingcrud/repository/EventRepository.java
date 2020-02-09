package pl.sda.javagda28.tourmanagingcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;

@Repository
public interface EventRepository extends JpaRepository <Event, Long>{
}
