package es.backend.repository;

import es.backend.model.Artista;
import es.backend.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {

    @Query("SELECT c FROM Cancion c WHERE NAME = ?1")
    Optional<Cancion> findByName(String name);

    @Query("SELECT c from Cancion c where NOMBRE like '%?1%'")
    Optional<Collection<Cancion>> searchByName(String name);

    @Query("SELECT c from Cancion c where ARTISTA like '%?1%'")
    Optional<Collection<Cancion>> searchByArtist(String name);

    @Query("SELECT c FROM Cancion c WHERE ALBUM = ?1")
    Optional<Collection<Cancion>> searchByAlbum(String album);

    //Obtener artistas de una cancion
    @Query("SELECT c.artistas FROM Cancion c WHERE NOMBRE = ?1")
    Optional<Collection<Artista>> getArtistas(String cancion);

    void deleteById(Integer id);

}
