package es.backend.controller;

import es.backend.model.Album;
import es.backend.model.Artist;
import es.backend.model.Song;
import es.backend.model.dto.AlbumDto;
import es.backend.model.dto.ArtistDto;
import es.backend.model.dto.SongDto;
import es.backend.model.request.ArtistRequest;
import es.backend.services.ArtistService;
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

@Controller("ArtistController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    private Logger log = LoggerFactory.getLogger(ArtistController.class);

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity addNewArtist (@RequestBody ArtistRequest artistRequest) {
        Optional<Artist> artistOptional = artistService.create(artistRequest.toEntity());
        if (artistOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ArtistDto(artistOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/get")
    public ResponseEntity getArtistById(Integer id) {
        Optional<Artist> artistOptional = artistService.getById(id);
        if (artistOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ArtistDto(artistOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/getByName")
    public ResponseEntity getArtistByName(String name) {
        Optional<List<Artist>> artistOptional = artistService.getByName(name);
        if (artistOptional.isPresent()) {
            List<ArtistDto> listArtistDto = new LinkedList<>();
            List<Artist> artists = artistOptional.get();
            for (int i = 0; i < artists.size(); i++) {
                listArtistDto.add(new ArtistDto(artists.get(i)));
            }
            return ResponseEntity.status(HttpStatus.OK).body(listArtistDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    /*
    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteArtist(@PathVariable Integer id) {
        if (artistService.deleteArtist(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
     */
}
