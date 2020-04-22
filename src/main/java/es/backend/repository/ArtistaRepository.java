package es.backend.repository;

import es.backend.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    @Query("SELECT c.artistas FROM Cancion c where nombre = ?1")
    Optional<Collection<Artista>> searchByCancion(String song);

    @Query("SELECT a FROM Artista a WHERE NAME = ?1")
    Optional<Collection<Artista>> searchByNombre(String name);

    Optional<Artista> findByNombre(String name);

    void deleteById(Integer id);

}
