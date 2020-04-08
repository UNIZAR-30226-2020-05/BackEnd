package es.backend.controller;

import es.backend.model.Song;
import es.backend.model.User;
import es.backend.model.dto.ListaCancionDto;
import es.backend.model.dto.SongDto;
import es.backend.model.dto.UserDto;
import es.backend.model.request.UserRequest;
import es.backend.services.SongService;
import es.backend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller("SongController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/song")
public class SongController {

    @Autowired
    private SongService songService;

    private Logger log = LoggerFactory.getLogger(SongController.class);
/*
    @PostMapping(path="/create")
    public @ResponseBody ResponseEntity addNewUser (@RequestBody UserRequest userRequest) {
        Optional<User> userOptional = userService.create(userRequest.toEntity());
         if (userOptional.isPresent()) {
             return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(userOptional.get()));
         } else {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
         }
    }
*/

    @GetMapping(path="/getByName")
    public ResponseEntity getSongByName(String name) {
        Optional<List<Song>> songsOptional = songService.getByName(name);
        if (songsOptional.isPresent()) {
            List<SongDto> listaSongDto = new LinkedList<>();
            List<Song> songs = songsOptional.get();
            for (int i = 0; i < songs.size(); i++) {
                listaSongDto.add(new SongDto(songs.get(i)));
            }
            return ResponseEntity.status(HttpStatus.OK).body(listaSongDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/getByArtist")
    public ResponseEntity getSongByArtist(String name) {
        Optional<List<Song>> songsOptional = songService.getByArtist(name);
        if (songsOptional.isPresent()) {
            List<SongDto> listaSongDto = new LinkedList<>();
            List<Song> songs = songsOptional.get();
            for (int i = 0; i < songs.size(); i++) {
                listaSongDto.add(new SongDto(songs.get(i)));
            }
            return ResponseEntity.status(HttpStatus.OK).body(listaSongDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
    /*
    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/findAll")
    public ResponseEntity<Collection<UserDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList()));
    }

     */
}