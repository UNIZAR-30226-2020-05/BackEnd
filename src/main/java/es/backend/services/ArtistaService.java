package es.backend.services;

import es.backend.model.Artista;
import es.backend.repository.ArtistaRepository;
import es.backend.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private CancionRepository cancionRepository;

    public Optional<Artista> create(Artista artist) { return Optional.of(artistaRepository.save(artist)); }

    public Optional<Collection<Artista>> getArtistsSong(String s) { return cancionRepository.getArtistas(s); }

    public Optional<Artista> getById(Integer id) { return artistaRepository.findById(id); }

    public List<Artista> getByName(String name) { return artistaRepository.searchByNombre(name); }

    @Transactional
    public Boolean deleteArtista(Integer id) {
        Optional<Artista> ar = artistaRepository.findById(id);
        if (ar.isPresent()) {
            deleteImagen(ar.get().getImagen());
            artistaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteImagen(String foto) {
        try{
            Path path = Paths.get("data/imagenes/artistas/"+foto);
            Files.delete(path);
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
