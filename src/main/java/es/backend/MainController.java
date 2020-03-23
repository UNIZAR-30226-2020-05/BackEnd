package es.backend;

import es.backend.request.*;
import es.backend.dto.*;
import es.backend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path="/user")
public class MainController {

    @Autowired
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(MainController.class);

    @PostMapping(path="/create") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestBody UserRequest userRequest) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        userService.create(userRequest.toEntity());
        return "Saved";
    }

    @GetMapping(path="/findAll")
    public ResponseEntity<Collection<UserDto>> getAllUsers() {
        // This returns a JSON or XML with the users
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList()));
    }
}