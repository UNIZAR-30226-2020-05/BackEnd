package es.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private Date date_added;

    @ManyToOne
    private Album album;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Date getDate_added() { return date_added; }

    public void setDate_added(Date date_added) { this.date_added = date_added; }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
