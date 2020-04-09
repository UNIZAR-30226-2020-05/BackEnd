package es.backend.repository;

import es.backend.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query("SELECT * from song where name = ?1")
    Optional<List<Song>> findByName(String name);

    //  FALTA DEFINIR COMO INTEGRAR ARTISTAS EN CANCIONES Y VICEVERSA
    @Query("SELECT * from song where artist = ?1")
    Optional<List<Song>> findByArtist(String name);

    void deleteById(Integer id);

}
