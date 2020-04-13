package es.backend.services;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import es.backend.model.ListaCancion;
import es.backend.model.dto.AmigoDto;
import es.backend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Album> getByTitulo_album(String titulo_album) {
        return albumRepository.findByTitulo(titulo_album);
    }

    public List<Album> getByArtista(Integer id_artista) {
        return albumRepository.findByArtista(artistaService.getById(id_artista).get());
    }

    public Optional<Album> add(Album album, Collection<Cancion> canciones, Integer id_artista){
        album = albumRepository.save(album);
        Optional<Artista> artistaOptional = artistaService.getById(id_artista);
        if (artistaOptional.isPresent()) {
            Iterator<Cancion> iterator = canciones.iterator();
            while(iterator.hasNext()){
                cancionService.create(iterator.next(), album, artistaOptional.get());
            }
            album.setArtista(artistaOptional.get());
        }
        return Optional.of(album);
    }
/*
    @Transactional
    public Boolean deleteAlbum(Integer id) {
        if (albumRepository.findById(id).isPresent()) {
            albumRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
*/
}
