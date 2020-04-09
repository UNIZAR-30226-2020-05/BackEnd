package es.backend.model.dto;

import es.backend.model.User;
import es.backend.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;

public class AmigoDto {


    @Autowired
    private SongService songService;

    public AmigoDto(User user) {
        this.id = user.getId();
        this.nick = user.getNick();
        if (user.getId_ultima_reproduccion() != null) {
            this.ultimaCancion = songService.getSong(user.getId_ultima_reproduccion()).get().getName();
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
