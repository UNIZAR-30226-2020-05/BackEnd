package es.backend.model.dto;

import es.backend.model.Artist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ArtistDto {

    public ArtistDto(Artist artist)
    {
         this.id = artist.getId();
         this.image_path = artist.getImage_path();
         this.name = artist.getName();
    }

    private Integer id;

    private String name;

    private String image_path;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getImage_path() { return image_path; }

    public void setImage_path(String image_path) { this.image_path = image_path; }

}
