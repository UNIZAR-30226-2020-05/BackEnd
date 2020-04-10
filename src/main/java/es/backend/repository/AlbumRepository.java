package es.backend.repository;

import es.backend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Optional<Album> findById(Integer id);

    //List<Album> findByTitulo_album(String tituloAlbum);

    //List<Album> findById_artista(Integer id_artista);

    //Optional<Album> addSongsToSongList(List<Song>);

    void deleteById(Integer id);

}
