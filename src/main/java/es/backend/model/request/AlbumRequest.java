package es.backend.model.request;

import es.backend.model.Album;

import java.util.ArrayList;

public class AlbumRequest {

    private Integer id_artista;

    private String titulo;

    private String caratula;

    private CancionRequest[] canciones;

    public Integer getId_artista() {
        return id_artista;
    }

    public void setId_artista(Integer id_artista) {
        this.id_artista = id_artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo_album) {
        this.titulo = titulo_album;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public CancionRequest[] getCanciones() {
        return canciones;
    }

    public void setCanciones(CancionRequest[] canciones) {
        this.canciones = canciones;
    }

    public Album toEntity() {
        Album album = new Album();
        album.setTitulo(titulo);
        album.setCaratula(caratula);
        return album;
    }
}
