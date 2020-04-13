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

    @ManyToMany
    private List<Artista> artistas = new ArrayList();

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
        System.out.println(a.getId());
        System.out.println(a.getNombre());
        this.artistas.add(a);
    }

    public void removeArtista(Artista a) { this.artistas.remove(a); }

}
