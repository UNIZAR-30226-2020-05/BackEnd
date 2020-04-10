package es.backend.model.dto;

import es.backend.model.ListaCancion;

public class ListaCancionDto {

    public ListaCancionDto(ListaCancion lista) {
        this.id = lista.getId();
        this.id_usuario = lista.getUsuario().getId();
        this.nombre = lista.getNombre();
    }

    private Integer id;

    private Integer id_usuario;

    private String nombre;

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
}
