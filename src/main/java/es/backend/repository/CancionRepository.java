package es.backend.repository;

import es.backend.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {

    @Query("SELECT * FROM CANCION WHERE NAME = ?1")
    Optional<Cancion> findByName(String name);

    //Buscar como introducir cadena parcial para buscar en los nombre
    @Query("SELECT * from CANCION where NOMBRE like '%?1%'")
    Optional<Collection<Cancion>> searchByName(String name);

    //Fallará porque correspondencia Song-Artist es en una tabla diferente de song
    @Query("SELECT * from CANCION where ARTISTA like '%?1%'")
    Optional<Collection<Cancion>> searchByArtist(String name);

    @Query("SELECT * FROM CANCION WHERE ALBUM = ?1")
    Optional<Collection<Cancion>> searchByAlbum(String album);

    void deleteById(Integer id);

}
