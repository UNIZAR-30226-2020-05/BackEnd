package es.backend.services;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import es.backend.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.sound.sampled.AudioInputStream;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CancionService {
    @Autowired
    private CancionRepository cancionRepository;

    public Optional<Cancion> create(Cancion cancion, Album album, Artista artista) {
        cancion.setAlbum(album);
        cancion.addArtista(artista);
        return Optional.of(cancionRepository.save(cancion));
    }

    public Optional<Cancion> getById(Integer id) { return cancionRepository.findById(id); }

    public Optional<Cancion> getByName(String name) { return cancionRepository.findByName(name); }

    public Optional<Collection<Cancion>> searchByName(String name) { return cancionRepository.searchByName(name); }

    public Optional<Collection<Cancion>> searchByArtist(String name) { return cancionRepository.searchByArtist(name); }

    public Optional<Collection<Cancion>> searchByAlbum(String name) { return cancionRepository.searchByAlbum(name); }

    public Optional<Collection<Cancion>> getAll() { return Optional.of(cancionRepository.findAll()); }

    public void deleteById(Integer id) { cancionRepository.deleteById(id); }

    @Transactional
    public void deleteByName(String name) {
        Optional<Cancion> c = getByName(name);
        c.ifPresent(cancion -> cancionRepository.delete(cancion));
    }

    public Optional<InputStreamResource> buscarCancion(String nombre) {
        try {
            return Optional.of(new InputStreamResource(new FileInputStream(
                    new File("src/main/resources/music/" + nombre + ".mp3"))));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
