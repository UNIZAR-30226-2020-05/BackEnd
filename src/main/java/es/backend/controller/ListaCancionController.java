package es.backend.controller;

import es.backend.model.Album;
import es.backend.model.ListaCancion;
import es.backend.model.User;
import es.backend.model.dto.AlbumDto;
import es.backend.model.dto.ListaCancionDto;
import es.backend.model.request.AlbumRequest;
import es.backend.model.request.ListaCancionRequest;
import es.backend.model.request.UserRequest;
import es.backend.model.dto.UserDto;
import es.backend.services.AlbumService;
import es.backend.services.ListaCancionService;
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

@Controller("ListaCancionController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/listaCancion")
public class ListaCancionController {

    @Autowired
    private ListaCancionService listaCancionService;

    private Logger log = LoggerFactory.getLogger(ListaCancionController.class);

    @PostMapping(path="/create")
    public @ResponseBody ResponseEntity addNewSongList (@RequestBody ListaCancionRequest listaCancionRequest) {
        Optional<ListaCancion> listaCancionOptional = listaCancionService.create(listaCancionRequest.toEntity(),
                listaCancionRequest.getId_usuario());
        if (listaCancionOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ListaCancionDto(listaCancionOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/get")
    public ResponseEntity getSongListById(Integer id) {
        Optional<ListaCancion> listaCancionOptional = listaCancionService.getById(id);
        if (listaCancionOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ListaCancionDto(listaCancionOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    //@PatchMapping(path="/add/{id_SongList}")
    //public ResponseEntity addSongToSongList(@PathVariable Integer id_SongList, Integer id_song) {
    //}

    //@PatchMapping(path="/add/{id_SongList}")
    //public ResponseEntity addAlbumToSongList(@PathVariable Integer id_SongList, Integer id_album) {
    //}

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteSongList(@PathVariable Integer id) {
        if (listaCancionService.deleteListaCancion(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
}
