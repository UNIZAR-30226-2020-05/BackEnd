package es.backend.model.request;

import es.backend.model.Artist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ArtistRequest {

    private Integer id;

    private String name;

    private String image;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getImage_path() { return image; }

    public void setImage_path(String image_path) { this.image = image_path; }

    public Artist toEntity(){
        Artist artist = new Artist();
        artist.setId(id);
        artist.setName(name);
        artist.setImage(image);
        return artist;
    }

}
