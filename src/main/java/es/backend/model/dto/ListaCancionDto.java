package es.backend.model.dto;

import es.backend.model.Cancion;
import es.backend.model.ListaCancion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ListaCancionDto {

    public ListaCancionDto(ListaCancion lista) {
        this.id = lista.getId();
        this.id_usuario = lista.getUsuario().getId();
        this.nombre = lista.getNombre();
        List<CancionDto> cancionesDto = new ArrayList<CancionDto>();
        List<Cancion> canc = lista.getCanciones();
        for(int i=0; i < canc.size();i++){
            cancionesDto.add(new CancionDto(canc.get(i)));
        }
        this.canciones = cancionesDto;
    }

    private Integer id;

    private Integer id_usuario;

    private String nombre;

    private List<CancionDto> canciones;

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
