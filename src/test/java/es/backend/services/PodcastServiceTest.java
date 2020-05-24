package es.backend.services;


import es.backend.model.Podcast;
import es.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PodcastServiceTest {
    @Autowired
    private PodcastService podcastService;

    @Test
    void testPodcastService() {
        //create
        Podcast podcast = new Podcast();
        podcast.setNombre("Podcast1");
        Optional<Podcast> optionalPodcast = podcastService.create(podcast);
        Assert.isTrue(optionalPodcast.isPresent(), "Podcast create: ERROR");
        //getById
        optionalPodcast = podcastService.getById(optionalPodcast.get().getId());
        Assert.isTrue(optionalPodcast.isPresent(),"Podcast getById: ERROR");
        //getByName
        optionalPodcast = podcastService.getByName(optionalPodcast.get().getNombre());
        Assert.isTrue(optionalPodcast.isPresent(),"Podcast getByName: ERROR");
        //searchByName
        Optional<Collection<Podcast>> listaPodcast = podcastService.searchByName(optionalPodcast.get().getNombre());
        Assert.isTrue(listaPodcast.isPresent() && !listaPodcast.get().isEmpty(),"Podcast searchByName: ERROR");
        //getAll
        listaPodcast = podcastService.getAll();
        Assert.isTrue(listaPodcast.isPresent() && !listaPodcast.get().isEmpty(),"Podcast getAll: ERROR");
        //deleteById
        Assert.isTrue(podcastService.deletePodcast(optionalPodcast.get().getId()),"Podcast deleteById: ERROR");
    }
}