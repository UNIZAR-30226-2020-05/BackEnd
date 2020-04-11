package es.backend.repository;

import es.backend.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {

   /* Optional<Cancion> findByName(String name);

    //Buscar como introducir cadena parcial para buscar en los nombre
    Optional<Collection<Cancion>> findByNombre(String name);

    //Fallará porque correspondencia Song-Artist es en una tabla diferente de song
    Optional<Collection<Cancion>> searchByArtist(String name);

    Optional<Collection<Cancion>> searchByAlbum(String album);
*/
    void deleteById(Integer id);

}
