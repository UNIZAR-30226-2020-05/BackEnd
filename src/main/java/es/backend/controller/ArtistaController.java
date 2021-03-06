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

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity getArtistaById(String id) {
        Integer idArtista = 0;
        try {
            idArtista = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mensaje del servidor: " + e);
        }
        Optional<Artista> artistOptional = artistService.getById(idArtista);
        if (artistOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ArtistaDto(artistOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteArtista(@PathVariable Integer id) {
        if (artistService.deleteArtista(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }


    @GetMapping(path="/getByName")
    public ResponseEntity<Collection<ArtistaDto>> getArtistaByName (String name) {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.getByName(name)
                .stream()
                .map(ArtistaDto::new)
                .collect(Collectors.toList()));
    }
}
