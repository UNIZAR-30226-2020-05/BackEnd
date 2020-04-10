package es.backend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ListaCancion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Cancion> canciones;

    private String nombre;

    public ListaCancion(Usuario usuario, String nombre) {
        this.usuario = usuario;
        this.nombre = nombre;
    }

    public ListaCancion(String nombre) {
        this.nombre = nombre;
    }

    public ListaCancion() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre; }

}
