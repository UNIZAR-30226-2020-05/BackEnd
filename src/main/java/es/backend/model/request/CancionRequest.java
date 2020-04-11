package es.backend.model.request;

import es.backend.model.Cancion;
import java.util.Date;
import java.util.List;

public class CancionRequest {

    private Integer id;

    private String nombre;

    private Date fecha_subida;

    private String album;

    private List<String> artistas;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFecha_subida() { return fecha_subida; }

    public void setFecha_subida(Date fecha_subida) { this.fecha_subida = fecha_subida; }

    public String getAlbum() { return album; }

    public void setAlbum(String album) { this.album = album; }

    public List<String> getArtistas() { return artistas; }

    public void setArtistas(List<String> artistas) { this.artistas = artistas; }

    public Cancion toEntity(){
        Cancion song = new Cancion();
        song.setNombre(nombre);
        song.setFecha_subida(fecha_subida);
        song.setAlbum(null);
        song.setArtistas(null);
        return song;
    }

}
