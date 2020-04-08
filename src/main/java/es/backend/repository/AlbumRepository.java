package es.backend.repository;

import es.backend.model.Album;
import es.backend.model.Song;
import es.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Optional<Album> findById(Integer id);

    List<Album> findByTitulo_album(String titulo_album);

    List<Album> findById_artista(Integer id_artista);

    //Optional<Album> addSongsToSongList(List<Song>);

    void deleteById(Integer id);

}
