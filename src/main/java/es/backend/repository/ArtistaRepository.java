package es.backend.repository;

import es.backend.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    @Query("SELECT artistas FROM Cancion where nombre = ?1")
    Optional<List<Artista>> searchBySong(String song);

    @Query("SELECT * FROM ARTISTA WHERE NAME = ?1")
    Optional<List<Artista>> findByName(String name);

    void deleteById(Integer id);

}
