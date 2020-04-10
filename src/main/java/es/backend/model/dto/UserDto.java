package es.backend.model.dto;

import es.backend.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    public UserDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nick = usuario.getNick();
        this.contrasena = usuario.getContrasena();
        this.tipo_user = usuario.getTipo_user();
        this.fecha_nacimiento = usuario.getFecha_nacimiento();
        this.id_ultima_reproduccion = usuario.getId_ultima_reproduccion();
        this.minuto_ultima_reproduccion = usuario.getMinuto_ultima_reproduccion();
        this.tipo_ultima_reproduccion = usuario.getTipo_ultima_reproduccion();
        if (usuario.getLista_cancion() != null) {
            this.lista_cancion = usuario.getLista_cancion()
                    .stream()
                    .map(ListaCancionDto::new)
                    .collect(Collectors.toList());
        }

        if (usuario.getAmigos() != null) {
            this.amigos = usuario.getAmigos()
                    .stream()
                    .map(AmigoDto::new)
                    .collect(Collectors.toList());
        }

    }

    private Integer id;

    private String nick;

    private String contrasena;

    private Boolean tipo_user;

    private String fecha_nacimiento;

    private Integer id_ultima_reproduccion;

    private Integer minuto_ultima_reproduccion;

    private Integer tipo_ultima_reproduccion;

    private List<ListaCancionDto> lista_cancion;

    private List<AmigoDto> amigos;

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

    public List<ListaCancionDto> getLista_cancion() {
        return lista_cancion;
    }

    public void setLista_cancion(List<ListaCancionDto> lista_cancion) {
        this.lista_cancion = lista_cancion;
    }

    public List<AmigoDto> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<AmigoDto> amigos) {
        this.amigos = amigos;
    }
}
