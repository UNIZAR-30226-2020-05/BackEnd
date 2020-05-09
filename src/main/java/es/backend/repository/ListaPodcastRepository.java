package es.backend.repository;

import es.backend.model.ListaPodcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListaPodcastRepository extends JpaRepository<ListaPodcast, Integer> {

    Optional<ListaPodcast> findByNombre(String nombre);

    void deleteById(Integer id);

}
