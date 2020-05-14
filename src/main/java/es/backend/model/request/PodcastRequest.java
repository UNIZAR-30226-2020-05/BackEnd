package es.backend.model.request;

import es.backend.model.Podcast;

import java.util.Date;
import java.util.List;

public class PodcastRequest {

    private String nombre;

    private String artista;

    private Date fecha_subida;

    private Integer duracion;

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getArtista() { return artista; }

    public void setArtista(String artista) { this.artista = artista; }

    public Date getFecha_subida() { return fecha_subida; }

    public void setFecha_subida(Date fecha_subida) { this.fecha_subida = fecha_subida; }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Podcast toEntity(){
        Podcast podcast = new Podcast();
        podcast.setNombre(nombre);
        podcast.setArtista(artista);
        podcast.setFecha_subida(fecha_subida);
        podcast.setDuracion(duracion);
        return podcast;
    }

}
