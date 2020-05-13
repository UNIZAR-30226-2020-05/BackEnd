package es.backend.model.dto;

import es.backend.model.Cancion;
import es.backend.model.Podcast;
import es.backend.model.Usuario;
import es.backend.services.CancionService;
import es.backend.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AmigoDto {


    @Autowired
    private CancionService cancionService;

    @Autowired
    private PodcastService podcastService;

    public AmigoDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nick = usuario.getNick();
        this.nombre = usuario.getNombre();
        this.apellidos = usuario.getApellidos();
        if (usuario.getId_ultima_reproduccion() != null) {
            Optional<Cancion> ultimaCancion = cancionService.getById(usuario.getId_ultima_reproduccion());
            if (ultimaCancion.isPresent()) {
                this.ultimaCancion = ultimaCancion.get().getNombre();
                this.artistaUltimaCancion = ultimaCancion.get().getArtistas().get(0).getNombre();
            } else {
                Optional<Podcast> ultimoPodcast = podcastService.getById(usuario.getId_ultima_reproduccion());
                if (ultimoPodcast.isPresent()) {
                    this.ultimaCancion = ultimoPodcast.get().getNombre();
                    this.artistaUltimaCancion = ultimoPodcast.get().getArtista();
                } else {
                    this.ultimaCancion = "";
                    this.artistaUltimaCancion = "";
                }
            }

        }
    }

    private Integer id;

    private String nick;

    private String nombre;

    private String apellidos;

    private String ultimaCancion;

    private String artistaUltimaCancion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUltimaCancion() {
        return ultimaCancion;
    }

    public void setUltimaCancion(String ultimaCancion) {
        this.ultimaCancion = ultimaCancion;
    }

    public String getArtistaUltimaCancion() {
        return artistaUltimaCancion;
    }

    public void setArtistaUltimaCancion(String artistaUltimaCancion) {
        this.artistaUltimaCancion = artistaUltimaCancion;
    }
}
