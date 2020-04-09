package es.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ListaCancion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    private String nombre;

    public ListaCancion(User user, String nombre) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
