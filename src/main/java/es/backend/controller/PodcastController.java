package es.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import es.backend.model.Album;
import es.backend.model.Cancion;
import es.backend.model.Podcast;
import es.backend.model.dto.AlbumDto;
import es.backend.model.dto.PodcastDto;
import es.backend.model.request.AlbumRequest;
import es.backend.model.request.CancionRequest;
import es.backend.model.request.PodcastRequest;
import es.backend.services.PodcastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller("PodcastController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/podcast")
public class PodcastController {

    @Autowired
    private PodcastService podcastService;

    private Logger log = LoggerFactory.getLogger(PodcastController.class);

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity add(@RequestBody PodcastRequest podcastRequest) throws JsonProcessingException {
        Optional<Podcast> podcastOptional = podcastService.create(podcastRequest.toEntity());
        if(podcastOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new PodcastDto(podcastOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/getByName")
    public ResponseEntity getPodcastByName(String name) {
        Optional<Collection<Podcast>> podcastsOptional = podcastService.searchByName(name);
        if (podcastsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(podcastsOptional.get()
                    .stream()
                    .map(PodcastDto::new)
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(value = "/play/{nombre}", produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity playAudio(@PathVariable String nombre) {
        Optional<InputStreamResource> inputOptional = podcastService.buscarPodcast(nombre);
        if (inputOptional.isPresent()) {
            return new ResponseEntity(inputOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @PostMapping(path="/upload")
    public @ResponseBody ResponseEntity upload (@RequestParam("file") MultipartFile file,
                                                @RequestParam("nombre") String nombre) {
        boolean guardado = podcastService.guardarPodcast(file, nombre);
        if (guardado) {
            return ResponseEntity.status(HttpStatus.CREATED).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deletePodcast(@PathVariable Integer id) {
        if (podcastService.deletePodcast(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
}