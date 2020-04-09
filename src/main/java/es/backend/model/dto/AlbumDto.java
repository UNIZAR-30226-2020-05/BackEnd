package es.backend.model.dto;

import es.backend.model.Album;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumDto {

    public AlbumDto(Album album) {
        this.id = album.getId();
        this.titulo_album = album.getTitulo_album();
        this.caratula = album.getCaratula();
        this.artista = new ArtistDto(album.getArtista());
        if (album.getCanciones() != null) {
            this.canciones = album.getCanciones()
                    .stream()
                    .map(SongDto::new)
                    .collect(Collectors.toList());
        }
    }

    private Integer id;

    private String titulo_album;

    private String caratula;

    private ArtistDto artista;

    private List<SongDto> canciones;

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

    public ArtistDto getArtista() {
        return artista;
    }

    public void setArtista(ArtistDto artista) {
        this.artista = artista;
    }

    public List<SongDto> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<SongDto> canciones) {
        this.canciones = canciones;
    }
}
