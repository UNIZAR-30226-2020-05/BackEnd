package es.backend.services;

import es.backend.model.Album;
import es.backend.model.ListaCancion;
import es.backend.model.Song;
import es.backend.model.User;
import es.backend.repository.AlbumRepository;
import es.backend.repository.ListaCancionRepository;
import es.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ListaCancionService {
    @Autowired
    private ListaCancionRepository listaCancionRepository;

    public Optional<ListaCancion> create(ListaCancion lista) {
        return Optional.of(listaCancionRepository.save(lista));
    }

    public Optional<ListaCancion> getById(Integer id) {
        return listaCancionRepository.findById(id);
    }

    //public Optional<> addSongToSongList(Integer id){
    //Añadir id de cancion a la lista
    //}

    //public Optional<> addAlbumToSongList(Integer id){
    //Añadir los id de las canciones pertenecientes al album a lista
    //}

    @Transactional
    public Boolean deleteListaCancion(Integer id) {
        if (listaCancionRepository.findById(id).isPresent()) {
            listaCancionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
