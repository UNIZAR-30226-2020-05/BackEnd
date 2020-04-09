package es.backend.services;

import es.backend.model.Artist;
import es.backend.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public Optional<Artist> create(Artist album) {
        return Optional.of(artistRepository.save(album));
    }


    public Optional<Artist> getById(Integer id) {
        return artistRepository.findById(id);
    }

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
