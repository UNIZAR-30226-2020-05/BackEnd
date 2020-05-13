package es.backend.model.dto;

import es.backend.model.Cancion;
import es.backend.model.Podcast;
import es.backend.model.Usuario;
import es.backend.services.UserService;

import java.util.Optional;

public class AmigoDto {

    public AmigoDto(Usuario usuario, UserService userService) {
        this.id = usuario.getId();
        this.nick = usuario.getNick();
        this.nombre = usuario.getNombre();
        this.apellidos = usuario.getApellidos();
        if (usuario.getTipo_ultima_reproduccion() != null) {
            if (usuario.getTipo_ultima_reproduccion() == 0) {
                Optional<Cancion> cancionOptional = userService.getUltimaCancion(usuario);
                if (cancionOptional.isPresent()) {
                    this.ultimaCancion = cancionOptional.get().getNombre();
                    this.artistaUltimaCancion = cancionOptional.get().getArtistas().get(0).getNombre();
                }
            } else if (usuario.getTipo_ultima_reproduccion() == 1) {
                Optional<Podcast> podcastOptional = userService.getUltimoPodcast(usuario);
                if (podcastOptional.isPresent()) {
                    this.ultimaCancion = podcastOptional.get().getNombre();
                    this.artistaUltimaCancion = podcastOptional.get().getArtista();
                }
            }
        }
    }

    private Integer id;

    private String nick;

    private String nombre;

    private String apellidos;

    private String ultimaCancion = "";

    private String artistaUltimaCancion = "";

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
