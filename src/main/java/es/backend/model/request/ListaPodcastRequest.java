package es.backend.model.request;

import es.backend.model.ListaPodcast;

public class ListaPodcastRequest {

    private Integer id_usuario;

    private String nombre;

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

    public ListaPodcast toEntity() {
        ListaPodcast lista = new ListaPodcast();
        lista.setNombre(nombre);
        return lista;
    }
}
