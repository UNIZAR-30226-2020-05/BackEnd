package es.backend.model.dto;

import es.backend.model.Album;
import es.backend.model.Artist;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistDto {

    public ArtistDto(Artist artist)
    {
         this.id = artist.getId();
         this.image_path = artist.getImage();
         this.name = artist.getName();
         if (artist.getAlbumes() != null) {
            this.albumes = artist.getAlbumes()
                    .stream()
                    .map(Album::getTitulo_album)
                    .collect(Collectors.toList());
         }
    }

    private Integer id;

    private String name;

    private String image_path;

    private List<String> albumes;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getImage_path() { return image_path; }

    public void setImage_path(String image_path) { this.image_path = image_path; }

    public List<String> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<String> albumes) {
        this.albumes = albumes;
    }
}
