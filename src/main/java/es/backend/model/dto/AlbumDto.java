package es.backend.model.dto;

import es.backend.model.Album;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumDto {

    public AlbumDto(Album album) {
        this.id = album.getId();
        this.titulo = album.getTitulo();
        this.caratula = album.getCaratula();
        this.artista = album.getArtista().getNombre();
        if (album.getCanciones() != null) {
            this.canciones = album.getCanciones()
                    .stream()
                    .map(CancionDto::new)
                    .collect(Collectors.toList());
        }
    }

    private Integer id;

    private String titulo;

    private String caratula;

    private String artista;

    private List<CancionDto> canciones;

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

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public List<CancionDto> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<CancionDto> canciones) {
        this.canciones = canciones;
    }
}
