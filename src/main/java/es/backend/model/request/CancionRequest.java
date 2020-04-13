package es.backend.model.request;

import es.backend.model.Artista;
import es.backend.model.Cancion;
import java.util.Date;
import java.util.List;

public class CancionRequest {

    private String nombre;

    private Date fecha_subida;

    private Integer duracion;

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFecha_subida() { return fecha_subida; }

    public void setFecha_subida(Date fecha_subida) { this.fecha_subida = fecha_subida; }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Cancion toEntity(){
        Cancion cancion = new Cancion();
        cancion.setNombre(nombre);
        cancion.setFecha_subida(fecha_subida);
        cancion.setDuracion(duracion);
        return cancion;
    }

}
