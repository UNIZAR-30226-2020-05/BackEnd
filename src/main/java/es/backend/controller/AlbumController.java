package es.backend.controller;

import es.backend.model.Album;
import es.backend.model.ListaCancion;
import es.backend.model.User;
import es.backend.model.dto.AlbumDto;
import es.backend.model.request.AlbumRequest;
import es.backend.model.request.UserRequest;
import es.backend.model.dto.UserDto;
import es.backend.services.AlbumService;
import es.backend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller("AlbumController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    private Logger log = LoggerFactory.getLogger(AlbumController.class);

    @PostMapping(path="/create")
    public @ResponseBody ResponseEntity addNewAlbum (@RequestBody AlbumRequest albumRequest) {
        Optional<Album> albumOptional = albumService.create(albumRequest.toEntity());
        if (albumOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new AlbumDto(albumOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/get")
    public ResponseEntity getAlbumById(Integer id) {
        Optional<Album> albumOptional = albumService.getById(id);
        if (albumOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new AlbumDto(albumOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    /*@GetMapping(path="/getByName")
    public ResponseEntity<Collection<AlbumDto>> getAlbumByTitulo_album(String nameAlbum) {
        return ResponseEntity.status(HttpStatus.OK).body(albumService.getByTitulo_album(nameAlbum)
                .stream()
                .map(AlbumDto::new)
                .collect(Collectors.toList()));
    }*/

    /*@GetMapping(path="/getByArtist")
    public ResponseEntity<Collection<AlbumDto>> getAlbumByTitulo_album(Integer id_artista) {
        return ResponseEntity.status(HttpStatus.OK).body(albumService.getById_artista(id_artista)
                .stream()
                .map(AlbumDto::new)
                .collect(Collectors.toList()));
    }*/

    //@PostMapping(path="/add")
    //public @ResponseBody ResponseEntity addSongsToAlbum (List<song> songList) {
    //}

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteAlbum(@PathVariable Integer id) {
        if (albumService.deleteAlbum(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
}
