package es.backend.model.request;

import es.backend.model.ListaCancion;

public class ListaCancionRequest {

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

    public ListaCancion toEntity() {
        ListaCancion lista = new ListaCancion();
        lista.setNombre(nombre);
        return lista;
    }
}
