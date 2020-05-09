package es.backend.model.dto;

import es.backend.model.ListaPodcast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaPodcastDto {

    public ListaPodcastDto(ListaPodcast lista) {
        this.id = lista.getId();
        this.id_usuario = lista.getUsuario().getId();
        this.nombre = lista.getNombre();
        if (lista.getPodcasts() != null) {
            this.podcasts = lista.getPodcasts()
                    .stream()
                    .map(PodcastDto::new)
                    .collect(Collectors.toList());
        }
    }

    private Integer id;

    private Integer id_usuario;

    private String nombre;

    private List<PodcastDto> podcasts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PodcastDto> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastDto> podcasts) {
        this.podcasts = podcasts;
    }
}
