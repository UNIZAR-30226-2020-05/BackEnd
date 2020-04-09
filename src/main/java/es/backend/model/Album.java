package es.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String titulo_album;

    private String caratula;

    @ManyToOne
    private Artist artista;

    @OneToMany(mappedBy = "album")
    private List<Song> canciones;

    //private List<Song> lista;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo_album() {
        return titulo_album;
    }

    public void setTitulo_album(String titulo_album) {
        this.titulo_album = titulo_album;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public Artist getArtista() {
        return artista;
    }

    public void setArtista(Artist artista) {
        this.artista = artista;
    }

    public List<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Song> canciones) {
        this.canciones = canciones;
    }
}
