package es.backend.services;

import ch.qos.logback.core.net.SyslogOutputStream;
import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import es.backend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private CancionService cancionService;

    public Optional<Album> getById(Integer id) {
        return albumRepository.findById(id);
    }

    public List<Album> searchByTitulo(String titulo) {
        return albumRepository.searchByTitulo(titulo);
    }

    public List<Album> getByArtista(Integer id_artista) {
        Optional<Artista> artistaOptional = artistaService.getById(id_artista);
        if (artistaOptional.isPresent()) {
            return artistaOptional.get().getAlbumes();
        }
        return new ArrayList<>();
    }

    public Optional<Album> create(Album album, List<Cancion> canciones, Integer id_artista){
        Optional<Artista> artistaOptional = artistaService.getById(id_artista);
        if (artistaOptional.isPresent()) {
            album.setArtista(artistaOptional.get());
            album = albumRepository.save(album);
            artistaOptional.get().addAlbumes(album);
            Iterator<Cancion> iterator = canciones.iterator();
            Optional<Cancion> optionalCancion;
            while(iterator.hasNext()){
                optionalCancion = cancionService.create(iterator.next(), album, artistaOptional.get());
                album.addCanciones(optionalCancion.get());
            }
            return Optional.of(album);
        }
        return Optional.empty();
    }

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
