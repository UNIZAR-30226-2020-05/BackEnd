package es.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ListaCancion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nombre;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "lista_cancion_canciones",
            joinColumns = { @JoinColumn(name = "lista_cancion_id") },
            inverseJoinColumns = { @JoinColumn(name = "cancion_id") })
    private List<Cancion> canciones = new ArrayList<Cancion>();

    public ListaCancion(String nombre) {
        this.nombre = nombre;
    }

    public ListaCancion() {}

    public Integer getId() {return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Cancion> getCanciones(){ return canciones; }

    public void addCancion(Cancion cancion){ canciones.add(cancion); }

    public boolean existeCancion(Cancion cancion){ return canciones.contains(cancion); }

    public void deleteCancion(Cancion cancion){ canciones.remove(cancion); }
}
