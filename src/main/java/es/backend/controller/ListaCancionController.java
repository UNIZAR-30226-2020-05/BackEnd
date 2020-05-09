package es.backend.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import es.backend.model.Cancion;
import es.backend.model.ListaCancion;
import es.backend.model.dto.ListaCancionDto;
import es.backend.model.dto.UsuarioDto;
import es.backend.model.request.ListaCancionRequest;
import es.backend.services.ListaCancionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
        Optional<ListaCancion> listaCancionOptional = listaCancionService.createByIdUser(
                listaCancionRequest.toEntity(), listaCancionRequest.getId_usuario());
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

    // Devuelve status = 304 (NOT_MODIFIED) si la cancion ya existía en la lista
    @PatchMapping(path="/add/{id_SongList}")
    public ResponseEntity addSongToSongList(@PathVariable Integer id_SongList, @RequestBody Integer id_song) {
        AtomicBoolean existeCancion = new AtomicBoolean();
        existeCancion.set(false);
        Optional<ListaCancion> listaCancionOptional = listaCancionService.addSong(id_SongList, id_song, existeCancion);
        if (listaCancionOptional.isPresent()){
            if(existeCancion.compareAndSet(true, true)){
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ListaCancionDto(listaCancionOptional.get()));
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(new ListaCancionDto(listaCancionOptional.get()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    // Devuelve status = 304 (NOT_MODIFIED) si todas las canciones del album ya estan en la lista
    // Devuelve status = 206 (PARTIAL_CONTENT) si alguna de las canciones del album ya esta en la lista
    @PatchMapping(path="/addByAlbum/{id_SongList}")
    public ResponseEntity addAlbumToSongList(@PathVariable Integer id_SongList, @RequestBody Integer id_album) {
        AtomicInteger existeCancion = new AtomicInteger();
        existeCancion.set(0);
        Optional<ListaCancion> listaCancionOptional = listaCancionService.addAlbum(id_SongList, id_album, existeCancion);
        if (listaCancionOptional.isPresent()){
            if(existeCancion.compareAndSet(2,2)){
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ListaCancionDto(listaCancionOptional.get()));
            }else if(existeCancion.compareAndSet(1,1)){
                return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(new ListaCancionDto(listaCancionOptional.get()));
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(new ListaCancionDto(listaCancionOptional.get()));
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteSongList(@PathVariable Integer id) {
        if (listaCancionService.deleteListaCancion(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @PatchMapping(path="/deleteSong/{id_SongList}")
    public ResponseEntity deleteSongToSongList(@PathVariable Integer id_SongList, @RequestBody Integer id_song) {
        Optional<ListaCancion> listaCancionOptional = listaCancionService.deleteCancionDeLista(id_SongList, id_song);
        if (listaCancionOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ListaCancionDto(listaCancionOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

}
