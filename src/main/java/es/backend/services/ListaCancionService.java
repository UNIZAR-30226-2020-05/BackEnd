package es.backend.services;

import es.backend.model.ListaCancion;
import es.backend.model.Usuario;
import es.backend.repository.ListaCancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ListaCancionService {
    @Autowired
    private ListaCancionRepository listaCancionRepository;

    @Autowired
    private UserService userService;

    public Optional<ListaCancion> create(ListaCancion listaCancion, Integer idUser) {
        listaCancion.setUsuario(userService.getById(idUser).get());
        return Optional.of(listaCancionRepository.save(listaCancion));
    }

    public Optional<ListaCancion> create(ListaCancion listaCancion, Usuario usuario) {
        listaCancion.setUsuario(usuario);
        return Optional.of(listaCancionRepository.save(listaCancion));
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
