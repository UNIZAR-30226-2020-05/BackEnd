package es.backend.services;

import es.backend.model.Song;
import es.backend.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    // Es necesario hacer Create como servicio?
    public Optional<Song> create(Song song) { return Optional.of(songRepository.save(song)); }

    public Optional<List<Song>> getByName(String name) { return songRepository.findByName(name); }

    public Optional<List<Song>> getByArtist(String name) { return songRepository.findByArtist(name); }

}
