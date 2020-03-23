package es.backend.dto;

import es.backend.User;

import java.util.Date;

public class UserDto {

    public UserDto(User user) {
        this.id = user.getId();
        this.nick = user.getNick();
        this.contrasena = user.getContrasena();
        this.tipo_user = user.getTipo_user();
        this.fecha_nacimiento = user.getFecha_nacimiento();
        this.id_ultima_reproduccion = user.getId_ultima_reproduccion();
        this.minuto_ultima_reproduccion = user.getMinuto_ultima_reproduccion();
        this.tipo_ultima_reproduccion = user.getTipo_ultima_reproduccion();
    }

    private Integer id;

    private String nick;

    private String contrasena;

    private Boolean tipo_user;

    private String fecha_nacimiento;

    private Integer id_ultima_reproduccion;

    private Integer minuto_ultima_reproduccion;

    private Integer tipo_ultima_reproduccion;

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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(Boolean tipo_user) {
        this.tipo_user = tipo_user;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Integer getId_ultima_reproduccion() {
        return id_ultima_reproduccion;
    }

    public void setId_ultima_reproduccion(Integer id_ultima_reproduccion) {
        this.id_ultima_reproduccion = id_ultima_reproduccion;
    }

    public Integer getMinuto_ultima_reproduccion() {
        return minuto_ultima_reproduccion;
    }

    public void setMinuto_ultima_reproduccion(Integer minuto_ultima_reproduccion) {
        this.minuto_ultima_reproduccion = minuto_ultima_reproduccion;
    }

    public Integer getTipo_ultima_reproduccion() {
        return tipo_ultima_reproduccion;
    }

    public void setTipo_ultima_reproduccion(Integer tipo_ultima_reproduccion) {
        this.tipo_ultima_reproduccion = tipo_ultima_reproduccion;
    }
}
