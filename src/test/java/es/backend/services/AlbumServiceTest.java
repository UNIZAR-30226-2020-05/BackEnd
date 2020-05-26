package es.backend.services;


import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import es.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlbumServiceTest {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private CancionService cancionService;

    @Test
    @Transactional
    void testAlbumService() {
        Cancion cancion = new Cancion();
        cancion.setNombre("Cancion1");
        List<Cancion> songs = new ArrayList<>();
        songs.add(cancion);
        Artista artista = new Artista();
        artista.setNombre("Artista1");
        Album album = new Album();
        album.setTitulo("Album1");
        //create artista
        Optional<Artista> optionalArtista = artistaService.create(artista);
        Assert.isTrue(optionalArtista.isPresent(), "Artista create: ERROR");
        List<Album> albums = new ArrayList<>();
        //Create con un artista que no existe
        Optional<Album> optionalAlbum = albumService.create(album, songs, 100);
        Assert.isTrue(!optionalAlbum.isPresent(), "Album create: ERROR");
        //create
        optionalAlbum = albumService.create(album, songs, optionalArtista.get().getId());
        Assert.isTrue(optionalAlbum.isPresent(), "Album create: ERROR");
        //getById
        optionalAlbum = albumService.getById(optionalAlbum.get().getId());
        Assert.isTrue(optionalAlbum.isPresent(), "Album getById: ERROR");
        //searchByTitulo
        albums = albumService.searchByTitulo(optionalAlbum.get().getTitulo());
        Assert.isTrue(!albums.isEmpty(), "Album searchByTitulo: ERROR");
        //getByArtista y artista tiene album
        albums = albumService.getByArtista(optionalArtista.get().getId());
        Assert.isTrue(!albums.isEmpty(), "Album getByArtista: ERROR");
        //getByArtista y artista no tiene album
        albums = albumService.getByArtista(100);
        Assert.isTrue(albums.isEmpty(), "Album getByArtista: ERROR");
        //deleteAlbum
        Assert.isTrue(albumService.deleteAlbum(optionalAlbum.get().getId()), "Album deleteAlbum: ERROR");
        Assert.isTrue(artistaService.deleteArtista(optionalArtista.get().getId()), "Artista delete: ERROR");
        //deleteAlbum que no existe
        Assert.isTrue(!albumService.deleteAlbum(100), "Album deleteAlbum: ERROR");
    }

}