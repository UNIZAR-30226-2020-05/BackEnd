package es.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cancion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nombre;

    private Date fecha_subida;

    private Integer duracion;

    @ManyToOne
    private Album album;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="cancion_artistas",
            joinColumns=@JoinColumn(name="canciones_id"),
            inverseJoinColumns=@JoinColumn(name="artistas_id")
    )
    private List<Artista> artistas = new ArrayList();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "lista_cancion_canciones",
            joinColumns = @JoinColumn(name = "cancion_id"),
            inverseJoinColumns = @JoinColumn(name = "lista_cancion_id"))
    private List<ListaCancion> cancionDeListas = new ArrayList<ListaCancion>();

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFecha_subida() { return fecha_subida; }

    public void setFecha_subida(Date fecha_subida) { this.fecha_subida = fecha_subida; }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Album getAlbum() { return album; }

    public void setAlbum(Album album) { this.album = album; }

    public List<Artista> getArtistas() { return artistas; }

    public void setArtistas(List<Artista> as) { this.artistas = as; }

    public void addArtista(Artista a) {
        this.artistas.add(a);
    }

    public void removeArtista(Artista a) { this.artistas.remove(a); }

    public List<ListaCancion> getCancionDeListas() {
        return cancionDeListas;
    }

    public void setCancionDeListas(List<ListaCancion> cancionDeListas) {
        this.cancionDeListas = cancionDeListas;
    }

    public void addCancionALista(ListaCancion listaCancion) {
        this.cancionDeListas.add(listaCancion);
    }

    public boolean deleteCancionALista(ListaCancion listaCancion) {
        if (this.cancionDeListas.contains(listaCancion)) {
            this.cancionDeListas.add(listaCancion);
            return true;
        }
        return false;
    }
}
