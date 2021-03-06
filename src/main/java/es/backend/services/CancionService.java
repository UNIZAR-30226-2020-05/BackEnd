package es.backend.services;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import es.backend.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.nio.file.Files;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

@Service
public class CancionService {

    private final Path music = Paths.get("data/music");

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    public Optional<Cancion> create(Cancion cancion, Album album, Artista artista) {
        cancion.setAlbum(album);
        cancion.addArtista(artista);
        return Optional.of(cancionRepository.save(cancion));
    }

    public Optional<Cancion> getById(Integer id) {
        Optional<Cancion> cancionOptional = cancionRepository.findById(id);
        return cancionOptional;
    }

    public Optional<Cancion> getByName(String name) { return cancionRepository.findByName(name); }

    public Optional<Collection<Cancion>> searchByName(String name) { return cancionRepository.searchByName(name); }

    public Optional<Collection<Cancion>> getAll() { return Optional.of(cancionRepository.findAll()); }

    public Boolean deleteById(Integer id) {
        if (cancionRepository.findById(id).isPresent()) {
            cancionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<InputStreamResource> buscarCancion(String nombre) {
        Resource resource = resourceLoader.getResource(
                "file:data/music/" + nombre + ".mp3");
        try {
            return Optional.of(new InputStreamResource(
                    new FileInputStream(resource.getFile())));
        } catch (IOException e) {
            System.out.println(e);
            return Optional.empty();
        }
    }

    public boolean guardarCancion(MultipartFile file, String nombre) {
        try {
            if (!Files.exists(music)) {
                Files.createDirectory(music);
            }
            Files.copy(file.getInputStream(), this.music.resolve(nombre + ".mp3"));
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

}
