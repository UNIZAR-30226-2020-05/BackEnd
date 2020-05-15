package es.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Usuario {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nombre;

    private String apellidos;

    private String nick;

    private String contrasena;

    private Boolean tipo_user;

    private Date fecha_nacimiento;

    private Integer id_ultima_reproduccion;

    private Integer minuto_ultima_reproduccion;

    private Integer tipo_ultima_reproduccion;

    private String nombre_avatar;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ListaCancion> lista_cancion = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ListaPodcast> lista_podcast = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="usuario_amigos",
            joinColumns=@JoinColumn(name="usuario_id"),
            inverseJoinColumns=@JoinColumn(name="amigos_id")
    )
    private List<Usuario> amigos = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="usuario_amigos",
            joinColumns=@JoinColumn(name="amigos_id"),
            inverseJoinColumns=@JoinColumn(name="usuario_id")
    )
    private List<Usuario> amigoDe = new ArrayList<>();

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

    public String getNombre_avatar() {
        return "imagenes/avatares/"+nombre_avatar;
    }

    public void setNombre_avatar(String nombre_avatar) {
        this.nombre_avatar = nombre_avatar;
    }

    public void setLastPlay(Integer id_play, Integer minuto_play, Integer tipo_play) {
        setId_ultima_reproduccion(id_play);
        setMinuto_ultima_reproduccion(minuto_play);
        setTipo_ultima_reproduccion(tipo_play);
    }

    public List<ListaCancion> getLista_cancion() {
        return lista_cancion;
    }

    public void setLista_cancion(List<ListaCancion> lista_cancion) {
        this.lista_cancion = lista_cancion;
    }

    public void addLista(ListaCancion listaCancion) {
        this.lista_cancion.add(listaCancion);
    }

    public List<ListaPodcast> getLista_podcast() {
        return lista_podcast;
    }

    public void setLista_podcast(List<ListaPodcast> lista_podcast) {
        this.lista_podcast = lista_podcast;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    public void addAmigo(Usuario usuario) {
        this.amigos.add(usuario);
        this.amigoDe.add(usuario);
    }

    public void deleteAmigo(Usuario usuario) {
        this.amigos.remove(usuario);
        this.amigoDe.remove(usuario);
    }

    public Integer esAmigo(Usuario usuario) {
        return this.amigos.indexOf(usuario);
    }

    public List<Usuario> getAmigoDe() {
        return amigoDe;
    }

    public void setAmigoDe(List<Usuario> amigoDe) {
        this.amigoDe = amigoDe;
    }
}
