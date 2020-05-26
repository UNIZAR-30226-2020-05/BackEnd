package es.backend.services;


import es.backend.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListaCancionServiceTest {
    @Autowired
    private ListaCancionService listaCancionService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserService userService;

    @Autowired
    private CancionService cancionService;

    @Test
    @Transactional
    void testListaCancionService(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario1");
        Optional<Usuario> optionalUsuario = userService.create(usuario);
        Assert.isTrue(optionalUsuario.isPresent(), "Usuario create: ERROR");
        ListaCancion listaCancion = new ListaCancion();
        listaCancion.setNombre("ListaCancion1");
        //create cuando usuario no existe
        Optional<ListaCancion> optionalListaCancion = listaCancionService.createByIdUser(listaCancion, 50);
        Assert.isTrue(!optionalListaCancion.isPresent(), "ListaCancion create: ERROR");
        //create
        optionalListaCancion = listaCancionService.createByIdUser(listaCancion, optionalUsuario.get().getId());
        Assert.isTrue(optionalListaCancion.isPresent(), "ListaCancion create: ERROR");
        //getById
        optionalListaCancion = listaCancionService.getById(optionalListaCancion.get().getId());
        Assert.isTrue(optionalListaCancion.isPresent(), "ListaCancion getById: ERROR");
        //addSong
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
        Optional<Cancion> optionalCancion = cancionService.getById(optionalAlbum.get().getCanciones().get(0).getId());
        Assert.isTrue(optionalCancion.isPresent(), "Cancion getById: ERROR");
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        //addsong a lista que no existe
        Optional<ListaCancion> optionalListaCancion1 = listaCancionService.addSong(100, optionalCancion.get().getId(), atomicBoolean);
        Assert.isTrue(!optionalListaCancion1.isPresent(), "ListaCancion addSong: ERROR");
        //addsong a lista que existe
        optionalListaCancion = listaCancionService.addSong(optionalListaCancion.get().getId(), optionalCancion.get().getId(), atomicBoolean);
        Assert.isTrue(optionalListaCancion.isPresent() && !optionalListaCancion.get().getCanciones().isEmpty(), "ListaCancion addSong: ERROR");
        //deleteCancionDeLista que no existe
        optionalListaCancion1 = listaCancionService.deleteCancionDeLista(optionalListaCancion.get().getId(), 100);
        Assert.isTrue(optionalListaCancion1.isPresent() && !optionalListaCancion.get().getCanciones().isEmpty(), "ListaCancion deleteCancionDeLista: ERROR");
        //deleteCancionDeLista
        optionalListaCancion = listaCancionService.deleteCancionDeLista(optionalListaCancion.get().getId(), optionalCancion.get().getId());
        Assert.isTrue(optionalListaCancion.isPresent() && optionalListaCancion.get().getCanciones().isEmpty(), "ListaCancion deleteCancionDeLista: ERROR");
        //addAlbum a lista que existe
        AtomicInteger atomicInteger = new AtomicInteger();
        optionalListaCancion = listaCancionService.addAlbum(optionalListaCancion.get().getId(), optionalAlbum.get().getId(), atomicInteger);
        Assert.isTrue(optionalListaCancion.isPresent() && !optionalListaCancion.get().getCanciones().isEmpty(), "ListaCancion addAlbum: ERROR");
        //addAlbum a lista que NO existe
        optionalListaCancion1 = listaCancionService.addAlbum(100, optionalAlbum.get().getId(), atomicInteger);
        Assert.isTrue(!optionalListaCancion1.isPresent(), "ListaCancion addAlbum: ERROR");
        //deleteListaCancion
        Assert.isTrue(listaCancionService.deleteListaCancion(optionalListaCancion.get().getId()), "ListaCancion delete: ERROR");
        Assert.isTrue(albumService.deleteAlbum(optionalAlbum.get().getId()), "Album delete: ERROR");
        Assert.isTrue(artistaService.deleteArtista(optionalArtista.get().getId()), "Artista delete: ERROR");
        Assert.isTrue(userService.deleteUser(optionalUsuario.get().getId()),"Usuario delete: ERROR");
        //deleteListaCancion que no existe
        Assert.isTrue(!listaCancionService.deleteListaCancion(100), "ListaCancion delete: ERROR");
    }

}