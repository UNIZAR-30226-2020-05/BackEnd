package es.backend.repository;

import es.backend.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    Optional<Artist> findById(Integer id);

    void deleteById(Integer id);

}
