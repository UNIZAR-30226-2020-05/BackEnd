package es.backend.services;

import es.backend.model.Album;
import es.backend.model.Song;
import es.backend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistService artistService;

    public Optional<Album> create(Album album, Integer idArtist) {
        album.setArtista(artistService.getById(idArtist).get());
        return Optional.of(albumRepository.save(album));
    }


    public Optional<Album> getById(Integer id) {
        return albumRepository.findById(id);
    }

    /*public List<Album> getByTitulo_album(String titulo_album) {
        return albumRepository.findByTitulo_album(titulo_album);
    }*/

    /*public List<Album> getById_artista(Integer id_artista) {
        return albumRepository.findById_artista(id_artista);
    }*/

    //@Transactional
    //public Optional<Album> addSongsToSongList(List<Song> SongList) {
    //    return albumRepository.addSongsToSongList(SongList);
    //}

    @Transactional
    public Boolean deleteAlbum(Integer id) {
        if (albumRepository.findById(id).isPresent()) {
            albumRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
