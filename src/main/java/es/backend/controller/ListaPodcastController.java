package es.backend.controller;

import es.backend.model.ListaCancion;
import es.backend.model.ListaPodcast;
import es.backend.model.dto.ListaCancionDto;
import es.backend.model.dto.ListaPodcastDto;
import es.backend.model.request.ListaCancionRequest;
import es.backend.model.request.ListaPodcastRequest;
import es.backend.services.ListaCancionService;
import es.backend.services.ListaPodcastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller("ListaPodcastController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/listaPodcast")
public class ListaPodcastController {

    @Autowired
    private ListaPodcastService listaPodcastService;

    private Logger log = LoggerFactory.getLogger(ListaPodcastController.class);

    @PostMapping(path="/create")
    public @ResponseBody ResponseEntity addNewPodcastList (@RequestBody ListaPodcastRequest listaPodcastRequest) {
        Optional<ListaPodcast> listaPodcastOptional = listaPodcastService.createByIdUser(
                listaPodcastRequest.toEntity(), listaPodcastRequest.getId_usuario());
        if (listaPodcastOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ListaPodcastDto(listaPodcastOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/get")
    public ResponseEntity getPodcastListById(Integer id) {
        Optional<ListaPodcast> listaPodcastOptional = listaPodcastService.getById(id);
        if (listaPodcastOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ListaPodcastDto(listaPodcastOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @PatchMapping(path="/add/{id_PodcastList}")
    public ResponseEntity addPodcastToSongList(@PathVariable Integer id_PodcastList, @RequestBody Integer id_Podcast) {
        Optional<ListaPodcast> listaPodcastOptional = listaPodcastService.addPodcast(id_PodcastList, id_Podcast);
        if (listaPodcastOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ListaPodcastDto(listaPodcastOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deletePodcastList(@PathVariable Integer id) {
        if (listaPodcastService.deleteListaPodcast(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @PatchMapping(path="/deletePodcast/{id_PodcastList}")
    public ResponseEntity deletePodcastToPodcastList(@PathVariable Integer id_PodcastList, @RequestBody Integer id_song) {
        Optional<ListaPodcast> listaPodcastOptional = listaPodcastService.deletePodcastDeLista(id_PodcastList, id_song);
        if (listaPodcastOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ListaPodcastDto(listaPodcastOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

}
