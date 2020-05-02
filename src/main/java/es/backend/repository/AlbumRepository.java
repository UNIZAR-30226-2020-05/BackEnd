package es.backend.repository;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Optional<Album> findById(Integer id);

    @Query("SELECT a from Album a where a.titulo like %?1%")
    List<Album> searchByTitulo(String titulo);

    @Query("SELECT a from Album a where a.artista = ?1")
    List<Album> findByArtista(Artista artista);

    //void deleteById(Integer id);

}
