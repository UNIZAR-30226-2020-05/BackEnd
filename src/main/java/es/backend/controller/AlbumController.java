package es.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import es.backend.model.dto.AlbumDto;
import es.backend.model.dto.CancionDto;
import es.backend.model.request.AlbumRequest;
import es.backend.model.request.CancionRequest;
import es.backend.services.AlbumService;
import es.backend.services.ArtistaService;
import es.backend.services.ImagenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private ImagenService imagenService;

    private Logger log = LoggerFactory.getLogger(AlbumController.class);

    @GetMapping(path="/get")
    public ResponseEntity getAlbumById(String id) {
        Integer idAlbum = 0;
        try {
            idAlbum = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mensaje del servidor: " + e);
        }
        Optional<Album> albumOptional = albumService.getById(idAlbum);
        if (albumOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new AlbumDto(albumOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    /*
    @GetMapping(value = "/getImg", produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity getFotoAlbum(String nombreAlbum) {
        Optional<InputStreamResource> inputOptional = imagenService.getFotoAlbum(nombreAlbum);
        if (inputOptional.isPresent()) {
            return new ResponseEntity(inputOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
     */

    @GetMapping(path="/getByTitulo")
    public ResponseEntity<List<AlbumDto>> getAlbumByTitulo(String titulo) {
        return ResponseEntity.status(HttpStatus.OK).body(albumService.searchByTitulo(titulo)
                .stream()
                .map(AlbumDto::new)
                .collect(Collectors.toList()));
    }

    @GetMapping(path="/getByArtist")
    public ResponseEntity<Collection<AlbumDto>> getAlbumByIdArtista(String id_artista) {
        Integer idArtista = 0;
        try {
            idArtista = Integer.parseInt(id_artista);
        } catch (NumberFormatException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mensaje del servidor: " + e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(albumService.getByArtista(idArtista)
                .stream()
                .map(AlbumDto::new)
                .collect(Collectors.toList()));
    }

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity add(@RequestBody AlbumRequest albumRequest) throws JsonProcessingException {

        List<Cancion> canciones = new ArrayList<>();
        for (CancionRequest cancionRequest : albumRequest.getCanciones()) {
            canciones.add(cancionRequest.toEntity());
        }
        Optional<Album> albumOptional = albumService.create(albumRequest.toEntity(), canciones, albumRequest.getId_artista());
        if(albumOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new AlbumDto(albumOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity deleteAlbum(@PathVariable Integer id) {
        if (albumService.deleteAlbum(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
}
