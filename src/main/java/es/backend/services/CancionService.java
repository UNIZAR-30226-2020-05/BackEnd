package es.backend.services;

import es.backend.model.Cancion;
import es.backend.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CancionService {
    @Autowired
    private CancionRepository cancionRepository;

    public Optional<Cancion> create(Cancion song) { return Optional.of(cancionRepository.save(song)); }

    public Optional<Cancion> getById(Integer id) { return cancionRepository.findById(id); }

   /* public Optional<Cancion> getByName(String name) { return cancionRepository.findByName(name); }

    public Optional<Collection<Cancion>> searchByName(String name) { return cancionRepository.searchByName(name); }

    public Optional<Collection<Cancion>> searchByArtist(String name) { return cancionRepository.searchByArtist(name); }

    public Optional<Collection<Cancion>> searchByAlbum(String name) { return cancionRepository.searchByAlbum(name); }
*/
    public Optional<Collection<Cancion>> getAll() { return Optional.of(cancionRepository.findAll()); }

    public void deleteById(Integer id) { cancionRepository.deleteById(id); }

    /*@Transactional
    public void deleteByName(String name) {
        Optional<Cancion> c = getByName(name);
        if(c.isPresent()) cancionRepository.delete(c.get());
    }*/

}
