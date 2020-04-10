package es.backend.services;

import es.backend.model.Artista;
import es.backend.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    public Optional<Artista> create(Artista artist) { return Optional.of(artistaRepository.save(artist)); }

    public Optional<List<Artista>> getArtistsSong(String s) { return artistaRepository.searchBySong(s); }

    public Optional<Artista> getById(Integer id) { return artistaRepository.findById(id); }

    public Optional<List<Artista>> getByName(String name) { return artistaRepository.findByName(name); }

    @Transactional
    public Boolean deleteArtista(Integer id) {
        if (artistaRepository.findById(id).isPresent()) {
            artistaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
