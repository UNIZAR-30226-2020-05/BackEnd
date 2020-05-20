package es.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Artista {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nombre;

    //Nombre de la imagen del artista
    private String imagen;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    private List<Album> albumes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="cancion_artistas",
            joinColumns=@JoinColumn(name="artistas_id"),
            inverseJoinColumns=@JoinColumn(name="canciones_id")
    )
    private List<Cancion> canciones;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String name) { this.nombre = name; }

    public String getImagen() { return "https://3.18.169.143:8443:8433/imagenes/artistas/"+imagen; }

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
