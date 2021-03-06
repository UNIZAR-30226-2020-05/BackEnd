package es.backend.model.dto;

import es.backend.model.Usuario;
import es.backend.services.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto {

    public UsuarioDto(Usuario usuario, UserService userService) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.apellidos = usuario.getApellidos();
        this.nick = usuario.getNick();
        this.contrasena = usuario.getContrasena();
        this.tipo_user = usuario.getTipo_user();
        this.fecha_nacimiento = usuario.getFecha_nacimiento();
        this.nombre_avatar = usuario.getNombre_avatar();
        this.id_ultima_reproduccion = usuario.getId_ultima_reproduccion();
        this.minuto_ultima_reproduccion = usuario.getMinuto_ultima_reproduccion();
        this.tipo_ultima_reproduccion = usuario.getTipo_ultima_reproduccion();
        if (usuario.getLista_cancion() != null) {
            this.lista_cancion = usuario.getLista_cancion()
                    .stream()
                    .map(ListaCancionDto::new)
                    .collect(Collectors.toList());
        }

        if (usuario.getLista_podcast() != null) {
            this.lista_podcast = usuario.getLista_podcast()
                    .stream()
                    .map(ListaPodcastDto::new)
                    .collect(Collectors.toList());
        }

        if (usuario.getAmigos() != null) {
            List<AmigoDto> amigoDtos = new ArrayList<>();
            for (Usuario amigo: usuario.getAmigos()) {
                amigoDtos.add(new AmigoDto(amigo, userService));
            }
            this.amigos = amigoDtos;
            /*this.amigos = usuario.getAmigos()
                    .stream()
                    .map(AmigoDto::new)
                    .collect(Collectors.toList());*/
        }

    }

    private Integer id;

    private String nombre;

    private String apellidos;

    private String nick;

    private String contrasena;

    private Boolean tipo_user;

    private Date fecha_nacimiento;

    private String nombre_avatar;

    private Integer id_ultima_reproduccion;

    private Integer minuto_ultima_reproduccion;

    private Integer tipo_ultima_reproduccion;

    private List<ListaCancionDto> lista_cancion;

    private List<ListaPodcastDto> lista_podcast;

    private List<AmigoDto> amigos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombre_avatar() {
        return nombre_avatar;
    }

    public void setNombre_avatar(String nombre_avatar) {
        this.nombre_avatar = nombre_avatar;
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

    public List<ListaPodcastDto> getLista_podcast() {
        return lista_podcast;
    }

    public void setLista_podcast(List<ListaPodcastDto> lista_podcast) {
        this.lista_podcast = lista_podcast;
    }

    public List<AmigoDto> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<AmigoDto> amigos) {
        this.amigos = amigos;
    }
}
