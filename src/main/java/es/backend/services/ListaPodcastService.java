package es.backend.services;

import es.backend.model.*;
import es.backend.repository.ListaPodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ListaPodcastService {
    @Autowired
    private ListaPodcastRepository listaPodcastRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PodcastService podcastService;


    public Optional<ListaPodcast> createByIdUser(ListaPodcast listaPodcast, Integer idUser) {
        Optional<Usuario> usuario = userService.getById(idUser);
        if (usuario.isPresent()) {
            listaPodcast.setUsuario(usuario.get());
            return Optional.of(listaPodcastRepository.save(listaPodcast));
        }
        return Optional.empty();
    }

    public Optional<ListaPodcast> create(ListaPodcast listaPodcast, Usuario usuario) {
        listaPodcast.setUsuario(usuario);
        return Optional.of(listaPodcastRepository.save(listaPodcast));
    }

    public Optional<ListaPodcast> getById(Integer id) {
        return listaPodcastRepository.findById(id);
    }

    @Transactional
    public Optional<ListaPodcast> addPodcast(Integer id_lista, Integer id_podcast) {
        Optional<Podcast> optionalPodcast = podcastService.getById(id_podcast);
        Optional<ListaPodcast> optionalListaPodcast = listaPodcastRepository.findById(id_lista);
        if(optionalPodcast.isPresent() && optionalListaPodcast.isPresent()){
            optionalListaPodcast.get().addPodcast(optionalPodcast.get());
            return optionalListaPodcast;
        } else {
            return optionalListaPodcast;
        }
    }

    @Transactional
    public Boolean deleteListaPodcast(Integer id) {
        if (listaPodcastRepository.findById(id).isPresent()) {
            listaPodcastRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Transactional
    public Optional<ListaPodcast> deletePodcastDeLista(Integer id_lista, Integer id_podcast) {
        Optional<Podcast> optionalPodcast = podcastService.getById(id_podcast);
        Optional<ListaPodcast> optionalListaPodcast = listaPodcastRepository.findById(id_lista);
        if (optionalPodcast.isPresent() && optionalListaPodcast.isPresent()) {
            optionalListaPodcast.get().deletePodcast(optionalPodcast.get());
            return optionalListaPodcast;
        } else {
            return optionalListaPodcast;
        }
    }
}
