package es.backend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer id_artista;

    private String titulo_album;

    private String caratula;

    //private List<Song> lista;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_artista() {
        return id_artista;
    }

    public void setId_artista(Integer id_artista) {
        this.id_artista = id_artista;
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

}
