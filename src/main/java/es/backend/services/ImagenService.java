package es.backend.services;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import es.backend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private ResourceLoader resourceLoader;

    //FALTA ELIMINAR FOTO DE ALBUM Y ARTISTA

    public Optional<InputStreamResource> getAvatar(String nombre) {
        Resource resource = resourceLoader.getResource(
                "file:imagenes/avatares" + nombre + ".jpg");
        try {
            return Optional.of(new InputStreamResource(
                    new FileInputStream(resource.getFile())));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public Optional<InputStreamResource> getFotoAlbum(String nombre) {
        Resource resource = resourceLoader.getResource(
                "file:imagenes/albumes" + nombre + ".jpg");
        try {
            return Optional.of(new InputStreamResource(
                    new FileInputStream(resource.getFile())));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public Optional<InputStreamResource> getFotoArtista(String nombre) {
        Resource resource = resourceLoader.getResource(
                "file:imagenes/artistas" + nombre + ".jpg");
        try {
            return Optional.of(new InputStreamResource(
                    new FileInputStream(resource.getFile())));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
