package es.backend.model.request;

import es.backend.model.Artista;
import java.util.List;

public class ArtistaRequest {

    private String nombre;

    private String imagen;

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getImagen() { return imagen; }

    public void setImagen(String imagen) { this.imagen = imagen; }

    public Artista toEntity(){
        Artista artist = new Artista();
        artist.setNombre(nombre);
        artist.setImagen(imagen);
        return artist;
    }
}
