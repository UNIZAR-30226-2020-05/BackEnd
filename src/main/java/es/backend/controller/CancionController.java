package es.backend.controller;

import es.backend.model.Cancion;
import es.backend.model.dto.CancionDto;
import es.backend.services.CancionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller("CancionController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/song")
public class CancionController {

    @Autowired
    private CancionService songService;

    private Logger log = LoggerFactory.getLogger(CancionController.class);

    /*@GetMapping(path="/getByName")
    public ResponseEntity getCancionByName(String name) {
        Optional<Collection<Cancion>> songsOptional = songService.searchByName(name);
        if (songsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(songsOptional.get()
                    .stream()
                    .map(CancionDto::new)
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/getByArtist")
    public ResponseEntity getCancionByArtist(String name) {
        Optional<Collection<Cancion>> songsOptional = songService.searchByArtist(name);
        if (songsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(songsOptional.get()
                    .stream()
                    .map(CancionDto::new)
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }*/
}