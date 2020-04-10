package es.backend.model.request;

import es.backend.model.Artista;
import java.util.List;

public class ArtistaRequest {

    private Integer id;

    private String nombre;

    private String imagen;

    private List<String> albumes;

    private List<String> canciones;


    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getImagen() { return imagen; }

    public void setImagen(String imagen) { this.imagen = imagen; }

    public List<String> getAlbumes() { return albumes; }

    public void setAlbumes(List<String> albumes) { this.albumes = albumes; }

    public List<String> getCanciones() { return canciones; }

    public void setCanciones(List<String> canciones) { this.canciones = canciones; }

    public Artista toEntity(){
        Artista artist = new Artista();
        artist.setId(id);
        artist.setNombre(nombre);
        artist.setImagen(imagen);
        return artist;
    }
}
