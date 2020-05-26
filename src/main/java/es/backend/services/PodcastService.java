package es.backend.services;

import es.backend.model.Podcast;
import es.backend.repository.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

@Service
public class PodcastService {

    private final Path podcast = Paths.get("data/podcast");

    @Autowired
    private PodcastRepository podcastRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    public Optional<Podcast> getById(Integer id) { return podcastRepository.findById(id); }

    public Optional<Podcast> getByName(String name) { return podcastRepository.findByName(name); }

    public Optional<Collection<Podcast>> searchByName(String name) { return podcastRepository.searchByName(name); }

    public Optional<Collection<Podcast>> getAll() { return Optional.of(podcastRepository.findAll()); }

    public void deleteById(Integer id) { podcastRepository.deleteById(id); }

    public Optional<Podcast> create(Podcast podcast) {
        return Optional.of(podcastRepository.save(podcast));
    }

    public Optional<InputStreamResource> buscarPodcast(String nombre) {
        Resource resource = resourceLoader.getResource(
                "file:data/podcast/" + nombre + ".mp3");
        try {
            return Optional.of(new InputStreamResource(
                    new FileInputStream(resource.getFile())));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public boolean guardarPodcast(MultipartFile file, String nombre) {
        try {
            if (!Files.exists(podcast)) {
                Files.createDirectory(podcast);
            }
            Files.copy(file.getInputStream(), this.podcast.resolve(nombre + ".mp3"));
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    @Transactional
    public Boolean deletePodcast(Integer id) {
        if (podcastRepository.findById(id).isPresent()) {
            podcastRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
