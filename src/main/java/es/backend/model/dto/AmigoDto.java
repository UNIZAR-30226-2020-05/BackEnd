package es.backend.model.dto;

import es.backend.model.Usuario;
import es.backend.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;

public class AmigoDto {


    @Autowired
    private CancionService cancionService;

    public AmigoDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nick = usuario.getNick();
        if (usuario.getId_ultima_reproduccion() != null) {
            this.ultimaCancion = cancionService.getById(usuario.getId_ultima_reproduccion()).get().getNombre();
        }
    }

    private Integer id;

    private String nick;

    private String ultimaCancion;

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

    public String getUltimaCancion() {
        return ultimaCancion;
    }

    public void setUltimaCancion(String ultimaCancion) {
        this.ultimaCancion = ultimaCancion;
    }
}
