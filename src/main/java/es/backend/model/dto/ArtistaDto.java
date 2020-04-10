package es.backend.model.dto;

import es.backend.model.Album;
import es.backend.model.Artista;
import es.backend.model.Cancion;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistaDto {

    public ArtistaDto(Artista a)
    {
         this.id = a.getId();
         this.imagen = a.getImagen();
         this.nombre = a.getNombre();
         if (a.getAlbumes() != null) {
            this.albumes = a.getAlbumes()
                    .stream()
                    .map(Album::getTitulo_album)
                    .collect(Collectors.toList());
         }
        if (a.getCanciones() != null) {
            this.canciones = a.getCanciones()
                    .stream()
                    .map(Cancion::getNombre)
                    .collect(Collectors.toList());
        }
    }

    private Integer id;

    private String nombre;

    private String imagen;

    private List<String> albumes;

    private List<String> canciones;

    public Integer getId() { return id; }

    //public void setId(Integer id) { this.id = id; }

    public String getName() { return nombre; }

    //public void setName(String name) { this.nombre = name; }

    public String getImage_path() { return imagen; }

    //public void setImage_path(String image_path) { this.imagen = image_path; }

    public List<String> getAlbumes() {
        return albumes;
    }

    //public void setAlbumes(List<String> albumes) { this.albumes = albumes; }

    public List<String> getCanciones() { return canciones; }
}
