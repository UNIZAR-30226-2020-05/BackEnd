package es.backend.controller;

import es.backend.model.Cancion;
import es.backend.model.Usuario;
import es.backend.model.dto.CancionDto;
import es.backend.model.dto.UsuarioDto;
import es.backend.model.request.UsuarioRequest;
import es.backend.services.CancionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Controller("CancionController")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/song")
public class CancionController {

    @Autowired
    private CancionService songService;

    private Logger log = LoggerFactory.getLogger(CancionController.class);

    @GetMapping(path="/getByName")
    public ResponseEntity getCancionByName(String name) {
        Optional<Collection<Cancion>> songsOptional = songService.searchByName(name);
        if (songsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(songsOptional.get()
                    .stream()
                    .map(CancionDto::new)
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @GetMapping(value = "/play/{nombre}", produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity playAudio(@PathVariable String nombre) {
        Optional<InputStreamResource> inputOptional = songService.buscarCancion(nombre);
        if (inputOptional.isPresent()) {
            return new ResponseEntity(inputOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @PostMapping(path="/upload")
    public @ResponseBody ResponseEntity upload (@RequestParam("file") MultipartFile file,
                                                @RequestParam("nombre") String nombre) {
        boolean guardado = songService.guardarCancion(file, nombre);
        if (guardado) {
            return ResponseEntity.status(HttpStatus.CREATED).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
}