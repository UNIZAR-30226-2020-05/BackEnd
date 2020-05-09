package es.backend.model.dto;

import es.backend.model.Podcast;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PodcastDto {

    public PodcastDto(Podcast podcast){
        this.id = podcast.getId();
        this.name = podcast.getNombre();
        this.fecha_subida = podcast.getFecha_subida();
        this.duracion = podcast.getDuracion();
    }

    private Integer id;

    private String name;

    private Date fecha_subida;

    private Integer duracion;

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

    public Date getFecha_subida() { return fecha_subida; }

    //public void setFecha_subida(Date fecha_subida) { this.fecha_subida = fecha_subida; }
}
