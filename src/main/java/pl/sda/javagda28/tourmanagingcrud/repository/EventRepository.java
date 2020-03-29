package pl.sda.javagda28.tourmanagingcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;

import java.util.Collection;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository <Event, Long>{
    List<Event> findByIdIn(Collection<Long> ids);
}
