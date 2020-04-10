package es.backend.model.dto;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CancionDto {

    public CancionDto(Cancion song){
        this.id = song.getId();
        this.name = song.getNombre();
        this.fecha_subida = song.getFecha_subida();
        this.album = song.getAlbum().getTitulo_album();
        if(song.getArtistas() != null){
            song.getArtistas()
                    .stream()
                    .map(Artista::getNombre)
            ;
        }
        //for(Artista x : song.getArtistas()){ artistas.add(x.getName()); }
    }

    private Integer id;

    private String name;

    private Date fecha_subida;

    private String album;

    private List<String> artistas;

    public Integer getId() { return id; }

    //public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    //public void setName(String name) { this.name = name; }

    public Date getDate_added() { return fecha_subida; }

    //public void setDate_added(Date date_added) { this.fecha_subida = date_added; }

    public String getAlbum() {
        return album;
    }

    //public void setAlbum(String album) { this.album = album; }

    public Date getFecha_subida() { return fecha_subida; }

    //public void setFecha_subida(Date fecha_subida) { this.fecha_subida = fecha_subida; }

    public List<String> getArtistas() { return artistas; }

    //public void setArtistas(List<String> artistas) { this.artistas = artistas; }
}
