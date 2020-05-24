package es.backend.services;

import org.junit.jupiter.api.Test;
import es.backend.services.ArtistaService;
import es.backend.model.Artista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArtistaServiceTest {
    @Autowired
    private ArtistaService artistaService;

    public Optional<Artista> optionalArtista;

    @Test
    void testArtistaService(){
        Artista a = new Artista();
        a.setNombre("Artista1");
        a.setImagen("/data/imagen");
        //Create
        Optional<Artista> optionalArtista = artistaService.create(a);
        Assert.isTrue(optionalArtista.isPresent(),"Artista create: ERROR");
        //GetById
        optionalArtista = artistaService.getById(optionalArtista.get().getId());
        Assert.isTrue(optionalArtista.isPresent(),"Artista getById: ERROR");
        //GetByName
        List<Artista> ArtistsList = artistaService.getByName(optionalArtista.get().getNombre());
        Assert.isTrue(!ArtistsList.isEmpty(),"Artista getByName: ERROR");
        //Delete
        Assert.isTrue(artistaService.deleteArtista(optionalArtista.get().getId()),"Artista delete: ERROR");
    }
}