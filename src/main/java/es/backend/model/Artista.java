package es.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Artista {

    //static private String path = "/fotos/artistas/"

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nombre;

    //Nombre de la imagen del artista
    private String imagen;

    @OneToMany(mappedBy = "artista")
    private List<Album> albumes;

    @ManyToMany(mappedBy = "artistas")
    private List<Cancion> canciones;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String name) { this.nombre = name; }

    public String getImagen() { return imagen; }

    public void setImagen(String image) { this.imagen = image; }

    public List<Album> getAlbumes() {
        return albumes;
    }

    //setAlbumes no es necesario al ser relación bidireccional
    //public void setAlbumes(List<Album> as) { this.albumes = as; }

    public List<Cancion> getCanciones() { return canciones; }

    //setCanciones no es necesario al ser relación bidireccional
    //public void setCanciones(List<Cancion> canciones) { this.canciones = canciones; }

}
