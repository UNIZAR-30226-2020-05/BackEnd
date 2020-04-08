package es.backend.model.dto;

import es.backend.model.Album;

public class AlbumDto {

    public AlbumDto(Album album) {
        this.id = album.getId();
        this.id_artista = album.getId_artista();
        this.titulo_album = album.getTitulo_album();
        this.caratula = album.getCaratula();
    }

    private Integer id;

    private Integer id_artista;

    private String titulo_album;

    private String caratula;

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
