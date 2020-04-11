package es.backend.repository;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Optional<Album> findById(Integer id);

    List<Album> findByTitulo(String titulo);

    List<Album> findByArtista(Artista artista);

    //void deleteById(Integer id);

}
