package es.backend.services;

import es.backend.model.Artist;
import es.backend.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public Optional<Artist> create(Artist artist) {
        return Optional.of(artistRepository.save(artist));
    }


    public Optional<Artist> getById(Integer id) {
        return artistRepository.findById(id);
    }

    public Optional<List<Artist>> getByName(String name) { return artistRepository.findByName(name); }

    @Transactional
    public Boolean deleteArtist(Integer id) {
        if (artistRepository.findById(id).isPresent()) {
            artistRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
