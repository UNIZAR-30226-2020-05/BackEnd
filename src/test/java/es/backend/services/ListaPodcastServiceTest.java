package es.backend.services;

import es.backend.model.ListaPodcast;
import es.backend.model.Podcast;
import es.backend.model.Usuario;
import org.junit.jupiter.api.Test;


import es.backend.repository.UsuarioRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListaPodcastServiceTest {
    @Autowired
    private ListaPodcastService listaPodcastService;

    @Autowired
    private PodcastService podcastService;

    @Autowired
    private UserService userService;

    @Test
    void testListaPodcastService() {
        //create
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario1");
        Optional<Usuario> optionalUsuario = userService.create(usuario);
        Assert.isTrue(optionalUsuario.isPresent(), "Usuario create: ERROR");
        ListaPodcast listaPodcast = new ListaPodcast();
        listaPodcast.setNombre("ListaPodcast1");
        Optional<ListaPodcast> optionalListaPodcast = listaPodcastService.createByIdUser(listaPodcast, optionalUsuario.get().getId());
        Assert.isTrue(optionalListaPodcast.isPresent(), "ListaPodcast create: ERROR");
        //getById
        optionalListaPodcast = listaPodcastService.getById(optionalListaPodcast.get().getId());
        Assert.isTrue(optionalListaPodcast.isPresent(),"ListaPodcast getById: ERROR");
        //addPodcast
        Podcast podcast = new Podcast();
        podcast.setNombre("Podcast1");
        Optional<Podcast> optionalPodcast = podcastService.create(podcast);
        Assert.isTrue(optionalPodcast.isPresent(), "Podcast create: ERROR");
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        optionalListaPodcast = listaPodcastService.addPodcast(optionalListaPodcast.get().getId(), optionalPodcast.get().getId(), atomicBoolean);
        Assert.isTrue(optionalListaPodcast.isPresent() && !optionalListaPodcast.get().getPodcasts().isEmpty(), "ListaPodcast addPodcast: ERROR");
        //deletePodcastDeLista
        optionalListaPodcast = listaPodcastService.deletePodcastDeLista(optionalListaPodcast.get().getId(), optionalPodcast.get().getId());
        Assert.isTrue(optionalListaPodcast.isPresent() && optionalListaPodcast.get().getPodcasts().isEmpty(), "ListaPodcast deletePodcastDeLista: ERROR");
        //deleteListaPodcast
        Assert.isTrue(listaPodcastService.deleteListaPodcast(optionalListaPodcast.get().getId()),"Podcast deleteById: ERROR");
    }
}