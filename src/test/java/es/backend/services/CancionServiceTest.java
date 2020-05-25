package es.backend.services;

import static org.junit.jupiter.api.Assertions.*;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CancionServiceTest {
    @Autowired
    private CancionService cancionService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private AlbumService albumService;

    @Test
    @Transactional
    void testCancionService(){
        //Create
        Artista artista = new Artista();
        artista.setNombre("Artista1");
        Optional<Artista> optionalArtista = artistaService.create(artista);
        Assert.isTrue(optionalArtista.isPresent(), "Artista create: ERROR");
        Album album = new Album();
        album.setTitulo("Album1");
        Cancion cancion = new Cancion();
        cancion.setNombre("Cancion1");
        List<Cancion> songs = new ArrayList<>();
        songs.add(cancion);
        Optional<Album> optionalAlbum = albumService.create(album, songs, optionalArtista.get().getId());
        Assert.isTrue(optionalAlbum.isPresent(), "Album create: ERROR");
        //getById
        Optional<Cancion> optionalCancion = cancionService.getById(optionalAlbum.get().getCanciones().get(0).getId());
        Assert.isTrue(optionalCancion.isPresent(), "Cancion getById: ERROR");
        //getByName
        optionalCancion = cancionService.getByName(optionalAlbum.get().getCanciones().get(0).getNombre());
        Assert.isTrue(optionalCancion.isPresent(), "Cancion getByName: ERROR");
        //searchByName
        Optional<Collection<Cancion>> optionalCanciones = cancionService.searchByName(optionalAlbum.get().getCanciones().get(0).getNombre());
        Assert.isTrue(optionalCanciones.isPresent() && !optionalCanciones.get().isEmpty(), "Cancion searchByName: ERROR");
        //getAll
        optionalCanciones = cancionService.getAll();
        Assert.isTrue(optionalCanciones.isPresent() && !optionalCanciones.get().isEmpty(), "Cancion getAll: ERROR");
        //deleteById
        Assert.isTrue(cancionService.deleteById(optionalCancion.get().getId()), "Cancion deleteById: ERROR");
        Assert.isTrue(albumService.deleteAlbum(optionalAlbum.get().getId()), "Album delete: ERROR");
        Assert.isTrue(artistaService.deleteArtista(optionalArtista.get().getId()), "Artista delete: ERROR");
    }

}