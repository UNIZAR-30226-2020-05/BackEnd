package es.backend.repository;

import es.backend.model.Artist;
import es.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    Optional<Artist> findById(Integer id);

    Optional<List<Artist>> findByName(String name);

    //Actualizar foto?
    @Modifying
    @Query("UPDATE Artist a set a.image = ?2 where a.id = ?1")
    Integer setArtistImageById(Integer id, String image);

    void deleteById(Integer id);

}
