package es.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Podcast {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nombre;

    private Date fecha_subida;

    private Integer duracion;

    private String artista;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "lista_podcast_podcasts",
            joinColumns = @JoinColumn(name = "podcast_id"),
            inverseJoinColumns = @JoinColumn(name = "lista_podcast_id"))
    private List<ListaPodcast> podcastDeListas = new ArrayList<ListaPodcast>();

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

    public List<ListaPodcast> getPodcastDeListas() {
        return podcastDeListas;
    }

    public void setPodcastDeListas(List<ListaPodcast> podcastDeListas) {
        this.podcastDeListas = podcastDeListas;
    }

    public void addPodcastALista(ListaPodcast listaPodcast) {
        this.podcastDeListas.add(listaPodcast);
    }

    public boolean deletePodcastALista(ListaPodcast listaPodcast) {
        if (this.podcastDeListas.contains(listaPodcast)) {
            this.podcastDeListas.add(listaPodcast);
            return true;
        }
        return false;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
}