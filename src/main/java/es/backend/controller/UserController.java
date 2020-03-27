package es.backend.controller;

import es.backend.model.User;
import es.backend.model.request.UserRequest;
import es.backend.model.dto.UserDto;
import es.backend.services.UserService;
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

@Controller("UserController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping(path="/create") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity addNewUser (@RequestBody UserRequest userRequest) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

         if (userService.create(userRequest.toEntity())) {
             return ResponseEntity.status(HttpStatus.OK).body("");
         } else {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
         }
    }

    @GetMapping(path="/get")
    public ResponseEntity getUserByNick(String nick) {
        Optional<User> userOptional = userService.getByNick(nick);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UserDto(userOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(path="/logIn")
    public ResponseEntity getUserLogIn(String nick, String pass) {
        Optional<User> userOptional = userService.getLogin(nick, pass);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UserDto(userOptional.get()));
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
}