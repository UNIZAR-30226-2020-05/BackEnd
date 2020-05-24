package es.backend.services;

import ch.qos.logback.core.net.SyslogOutputStream;
import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import es.backend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            Iterator<Cancion> iterator = canciones.iterator();
            while(iterator.hasNext()){
                cancionService.create(iterator.next(), album, artistaOptional.get());
            }
            album.setArtista(artistaOptional.get()); //
            return Optional.of(album);
        }
        return Optional.empty();
    }

    @Transactional
    public Boolean deleteAlbum(Integer id) {
        Optional<Album> al = albumRepository.findById(id);
        if (al.isPresent()) {
            deleteImagen(al.get().getCaratula());
            albumRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteImagen(String foto) {
        try{
            Path path = Paths.get("data/imagenes/albums/"+foto);
            Files.delete(path);
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
