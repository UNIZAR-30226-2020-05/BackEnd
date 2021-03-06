package es.backend.controller;

import es.backend.model.Usuario;
import es.backend.model.request.UsuarioRequest;
import es.backend.model.dto.UsuarioDto;
import es.backend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller("UserController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/user")
public class UsuarioController {

    @Autowired
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping(path="/create")
    public @ResponseBody ResponseEntity addNewUser (@RequestBody UsuarioRequest usuarioRequest) {
        Optional<Usuario> userOptional = userService.create(usuarioRequest.toEntity());
         if (userOptional.isPresent()) {
             return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDto(userOptional.get(),
                     userService));
         } else {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
         }
    }

    @GetMapping(path="/get")
    public ResponseEntity getUserByNick(String nick) {
        Optional<Usuario> userOptional = userService.getByNick(nick);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(userOptional.get(),
                    userService));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/logIn")
    public ResponseEntity getUserLogIn(String nick, String pass) {
        Optional<Usuario> userOptional = userService.getLogin(nick, pass);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(userOptional.get(),
                    userService));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @PatchMapping(path="/modifyPass/{id}")
    public ResponseEntity modifyPassword(@PathVariable Integer id, @RequestBody String pass) {
        Optional<Usuario> userOptional = userService.setUserPasswordById(id, pass);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(userOptional.get(),
                    userService));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @PatchMapping(path="/addAmigo/{id1}")
    public ResponseEntity addAmigo(@PathVariable Integer id1, @RequestBody Integer id2) {
        Optional<Usuario> userOptional = userService.addAmigos(id1, id2);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(userOptional.get(),
                    userService));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @PatchMapping(path="/deleteAmigo/{id1}")
    public ResponseEntity deleteAmigo(@PathVariable Integer id1, @RequestBody Integer id2) {
        Optional<Usuario> userOptional = userService.deleteAmigos(id1, id2);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(userOptional.get(),
                    userService));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @PatchMapping(path="/setAvatar/{id}")
    public ResponseEntity addAmigo(@PathVariable Integer id, @RequestBody String avatar) {
        Optional<Usuario> userOptional = userService.setAvatar(id, avatar);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(userOptional.get(),
                    userService));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/findAll")
    public ResponseEntity<Collection<UsuarioDto>> getAllUsers() {
        List<UsuarioDto> usuarioDtos = new ArrayList<>();
        for (Usuario usuario : userService.findAll()) {
            usuarioDtos.add(new UsuarioDto(usuario, userService));
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDtos);
    }

    @PatchMapping(path="/modifyLastPlayAndroid/{id}")
    public ResponseEntity modifyLastPlayAndroid(@PathVariable Integer id, @RequestBody String play) {
        String playSplit[] = play.split(";");
        return modifyLastPlay(id, playSplit[0], playSplit[1], playSplit[2]);
    }

    @PatchMapping(path="/modifyLastPlay/{id}")
    public ResponseEntity modifyLastPlay(@PathVariable Integer id, @RequestParam String id_play,
                                         @RequestParam String minuto_play, @RequestParam String tipo_play) {
        Integer idPlay = 0;
        Integer minutoPlay = 0;
        Integer tipoPlay = 0;
        try {
            idPlay = Integer.parseInt(id_play);
            minutoPlay = Integer.parseInt(minuto_play);
            tipoPlay = Integer.parseInt(tipo_play);
        } catch (NumberFormatException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mensaje del servidor: " + e);
        }
        Optional<Usuario> userOptional = userService.modifyLastPlay(id, idPlay, minutoPlay, tipoPlay);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDto(userOptional.get(),
                    userService));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
}