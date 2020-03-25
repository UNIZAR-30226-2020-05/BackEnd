package es.backend.model.request;

import es.backend.model.Song;

import java.util.Date;

public class SongRequest {

    private Integer id;

    private String name;

    private Date date_added;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Date getDate_added() { return date_added; }

    public void setDate_added(Date date_added) { this.date_added = date_added; }

    public Song toEntity(){
        Song song = new Song();
        song.setName(name);
        song.setDate_added(date_added);
        return song;
    }

}
