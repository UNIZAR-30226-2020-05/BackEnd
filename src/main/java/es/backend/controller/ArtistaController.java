package es.backend.controller;

import es.backend.model.Artista;
import es.backend.model.dto.ArtistaDto;
import es.backend.model.request.ArtistaRequest;
import es.backend.services.ArtistaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller("ArtistaController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/artist")
public class ArtistaController {

    @Autowired
    private ArtistaService artistService;

    private Logger log = LoggerFactory.getLogger(ArtistaController.class);

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity addNewArtista (@RequestBody ArtistaRequest artistRequest) {
        Optional<Artista> artistOptional = artistService.create(artistRequest.toEntity());
        if (artistOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ArtistaDto(artistOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/get")
    public ResponseEntity getArtistaById(Integer id) {
        Optional<Artista> artistOptional = artistService.getById(id);
        if (artistOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ArtistaDto(artistOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

   /* @GetMapping(path="/getByName")
    public ResponseEntity getArtistaByName(String name) {
        Optional<List<Artista>> artistOptional = artistService.getByName(name);
        if (artistOptional.isPresent()) {
            List<ArtistaDto> listArtistaDto = new LinkedList<>();
            List<Artista> artists = artistOptional.get();
            for (int i = 0; i < artists.size(); i++) {
                listArtistaDto.add(new ArtistaDto(artists.get(i)));
            }
            return ResponseEntity.status(HttpStatus.OK).body(listArtistaDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }*/
}
