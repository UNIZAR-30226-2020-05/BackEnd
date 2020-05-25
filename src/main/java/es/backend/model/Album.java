package es.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Album {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String titulo;

    private String caratula;

    @ManyToOne
    private Artista artista;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Cancion> canciones = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo_album) {
        this.titulo = titulo_album;
    }

    public String getCaratula() { return "https://3.18.169.143:8443:8433/imagenes/albums/"+caratula; }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public void addCanciones(Cancion cancion){ canciones.add(cancion);}

}
