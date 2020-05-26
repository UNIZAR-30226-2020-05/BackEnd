package es.backend.services;

import es.backend.model.*;
import es.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private CancionService cancionService;

    @Autowired
    private PodcastService podcastService;

    @Test
    @Transactional
    void testUserService(){
        //usuario
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario1");
        usuario.setNick("nick");
        usuario.setContrasena("pass");
        //amigo
        Usuario amigo = new Usuario();
        usuario.setNombre("Usuario2");
        //Create
        Optional<Usuario> optionalUsuario = userService.create(usuario);
        Assert.isTrue(optionalUsuario.isPresent(),"Usuario create: ERROR");
        Optional<Usuario> optionalAmigo = userService.create(amigo);
        Assert.isTrue(optionalUsuario.isPresent(),"Amigo create: ERROR");
        //getById
        optionalUsuario = userService.getById(optionalUsuario.get().getId());
        Assert.isTrue(optionalUsuario.isPresent(),"Usuario getById: ERROR");
        //getByNick
        optionalUsuario = userService.getByNick(optionalUsuario.get().getNick());
        Assert.isTrue(optionalUsuario.isPresent(),"Usuario getByNick: ERROR");
        //getLogin
        optionalUsuario = userService.getLogin(optionalUsuario.get().getNick(), optionalUsuario.get().getContrasena());
        Assert.isTrue(optionalUsuario.isPresent(),"Usuario getLogin: ERROR");
        //setUserPasswordById
        String newPass = "nuevaPass";
        optionalUsuario = userService.setUserPasswordById(optionalUsuario.get().getId(), newPass);
        Assert.isTrue(optionalUsuario.isPresent() && optionalUsuario.get().getContrasena().contains(newPass),"Usuario setUserPasswordById: ERROR");
        //getLogin Despues de cambiar contraseña
        optionalUsuario = userService.getLogin(optionalUsuario.get().getNick(), newPass);
        Assert.isTrue(optionalUsuario.isPresent(),"Usuario getLogin: ERROR TRAS CAMBIAR CONTRASEÑA");
        //addAmigos
        optionalUsuario = userService.addAmigos(optionalUsuario.get().getId(), optionalAmigo.get().getId());
        Assert.isTrue(optionalUsuario.isPresent() && !optionalUsuario.get().getAmigos().isEmpty(),"Usuario addAmigos: ERROR");
        //deleteAmigos
        optionalUsuario = userService.deleteAmigos(optionalUsuario.get().getId(), optionalAmigo.get().getId());
        Assert.isTrue(optionalUsuario.isPresent() && optionalUsuario.get().getAmigos().isEmpty(),"Usuario deleteAmigos: ERROR");
        //findAll
        List<Usuario> listaUsers = userService.findAll();
        Assert.isTrue(listaUsers.size() == 2,"Usuario findAll: ERROR");
        //modifyLastPlay && getUltimaCancion
        Artista artista = new Artista();
        artista.setNombre("Artista1");
        Optional<Artista> optionalArtista = artistaService.create(artista);
        Assert.isTrue(optionalArtista.isPresent(),"Artista create: ERROR");
        Cancion cancion = new Cancion();
        cancion.setNombre("Cancion1");
        cancion.setDuracion(90);
        cancion.setFecha_subida(new Date());
        List<Cancion> songs = new ArrayList<>();
        songs.add(cancion);
        Album album = new Album();
        album.setTitulo("Album1");
        Optional<Album> optionalAlbum = albumService.create(album, songs, optionalArtista.get().getId()); // <<------
        Assert.isTrue(optionalAlbum.isPresent(),"Album create: ERROR");
        Assert.isTrue(!optionalAlbum.get().getCanciones().isEmpty(), "El album esta vacio: ERROR, deberia de contener una cancion.");
        cancion = optionalAlbum.get().getCanciones().get(0);
        //getUltimaCancion cuando esta a null
        Assert.isTrue(!userService.getUltimaCancion(optionalUsuario.get()).isPresent(), "getUltimaCancion: ERROR");
        optionalUsuario = userService.modifyLastPlay(optionalUsuario.get().getId(), cancion.getId(), 90, 1);
        Assert.isTrue(optionalUsuario.isPresent() && userService.getUltimaCancion(optionalUsuario.get()).get().getNombre().contains(cancion.getNombre()),"Usuario modifyLastPlay && getUltimaCancion: ERROR");
        //modifylastplay(cancion) de usuario que no existe
        Optional<Usuario> optionalUsuario1 = userService.modifyLastPlay(100, cancion.getId(), 90, 1);
        Assert.isTrue(!optionalUsuario1.isPresent(), "Usuario modifyLastPlay && getUltimaCancion: ERROR");
        //modifyLastPlay && getUltimoPodcast
        Podcast podcast = new Podcast();
        podcast.setNombre("Podcast1");
        Optional<Podcast> optionalPodcast = podcastService.create(podcast);
        Assert.isTrue(optionalPodcast.isPresent(),"Podcast create: ERROR");
        //getUltimaPodcast cuando esta a null
        Assert.isTrue(!userService.getUltimoPodcast(optionalUsuario.get()).isPresent(), "getUltimoPodcast: ERROR");
        optionalUsuario = userService.modifyLastPlay(optionalUsuario.get().getId(), podcast.getId(), 90, 2);
        Assert.isTrue(optionalUsuario.isPresent() && userService.getUltimoPodcast(optionalUsuario.get()).get().getNombre().contains(podcast.getNombre()),"Usuario modifyLastPlay && getUltimoPodcast: ERROR");
        //modifylastplay(podcast) de usuario que no existe
        optionalUsuario1 = userService.modifyLastPlay(100, podcast.getId(), 90, 1);
        Assert.isTrue(!optionalUsuario1.isPresent(), "Usuario modifyLastPlay && getUltimoPodcast: ERROR");
        //setAvatar
        optionalUsuario = userService.setAvatar(optionalUsuario.get().getId(), "Avatar1");
        Assert.isTrue(optionalUsuario.isPresent() && optionalUsuario.get().getNombre_avatar().contains("Avatar1"),"Usuario setAvatar: ERROR");
        //delete
        Assert.isTrue(userService.deleteUser(optionalUsuario.get().getId()),"Usuario delete: ERROR");
        Assert.isTrue(userService.deleteUser(optionalAmigo.get().getId()),"Amigo delete: ERROR");
        Assert.isTrue(podcastService.deletePodcast(optionalPodcast.get().getId()), "Podcast delete: ERROR");
        Assert.isTrue(albumService.deleteAlbum(optionalAlbum.get().getId()), "Album delete: ERROR");
        Assert.isTrue(artistaService.deleteArtista(optionalArtista.get().getId()), "Album delete: ERROR");
        //delete de un usuario que no existe
        Assert.isTrue(!userService.deleteUser(100),"Usuario delete: ERROR");
    }
}