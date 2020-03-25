package es.backend.model.request;

import es.backend.model.User;

public class UserRequest {

    private Integer id;

    private String nick;

    private String contrasena;

    private Boolean tipo_user;

    private String fecha_nacimiento;

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

    public User toEntity() {
        User user = new User();
        user.setNick(nick);
        user.setContrasena(contrasena);
        user.setTipo_user(tipo_user);
        user.setFecha_nacimiento(fecha_nacimiento);
        return user;
    }

}
