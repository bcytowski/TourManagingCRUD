package pl.sda.javagda28.tourmanagingcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, String> {
}
