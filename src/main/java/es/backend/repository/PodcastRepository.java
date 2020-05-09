package es.backend.repository;

import es.backend.model.Artista;
import es.backend.model.Cancion;
import es.backend.model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface PodcastRepository extends JpaRepository<Podcast, Integer> {

    @Query("SELECT p FROM Podcast p WHERE p.nombre = ?1")
    Optional<Podcast> findByName(String name);

    @Query("SELECT p from Podcast p where p.nombre like %?1%")
    Optional<Collection<Podcast>> searchByName(String name);

    void deleteById(Integer id);

}
