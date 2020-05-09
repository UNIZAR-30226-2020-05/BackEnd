package es.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ListaPodcast {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nombre;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "lista_podcast_podcasts",
            joinColumns = { @JoinColumn(name = "lista_podcast_id") },
            inverseJoinColumns = { @JoinColumn(name = "podcast_id") })
    private List<Podcast> podcasts = new ArrayList<Podcast>();

    public ListaPodcast(String nombre) {
        this.nombre = nombre;
    }

    public ListaPodcast() {}

    public Integer getId() {return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Podcast> getPodcasts(){ return podcasts; }

    public void addPodcast(Podcast podcast){ podcasts.add(podcast); }

    public boolean existePodcast(Podcast podcast){ return podcasts.contains(podcast); }

    public void deletePodcast(Podcast podcast){ podcasts.remove(podcast); }
}