package es.backend.model.dto;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CancionDto {

    public CancionDto(Cancion cancion){
        this.id = cancion.getId();
        this.name = cancion.getNombre();
        this.fecha_subida = cancion.getFecha_subida();
        this.duracion = cancion.getDuracion();
        this.album = cancion.getAlbum().getTitulo();
        if (cancion.getArtistas() != null) {
            this.artistas = cancion.getArtistas()
                    .stream()
                    .map(Artista::getNombre)
                    .collect(Collectors.toList());
        }
    }

    private Integer id;

    private String name;

    private Date fecha_subida;

    private Integer duracion;

    private String album;

    private List<String> artistas;

    public Integer getId() { return id; }

    //public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    //public void setName(String name) { this.name = name; }

    public Date getDate_added() { return fecha_subida; }

    //public void setDate_added(Date date_added) { this.fecha_subida = date_added; }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getAlbum() {
        return album;
    }

    //public void setAlbum(String album) { this.album = album; }

    public Date getFecha_subida() { return fecha_subida; }

    //public void setFecha_subida(Date fecha_subida) { this.fecha_subida = fecha_subida; }

    public List<String> getArtistas() { return artistas; }

    //public void setArtistas(List<String> artistas) { this.artistas = artistas; }
}
