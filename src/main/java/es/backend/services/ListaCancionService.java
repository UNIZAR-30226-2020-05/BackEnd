package es.backend.services;

import es.backend.model.Album;
import es.backend.model.ListaCancion;
import es.backend.model.Cancion;
import es.backend.repository.AlbumRepository;
import es.backend.model.Usuario;
import es.backend.repository.ListaCancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ListaCancionService {
    @Autowired
    private ListaCancionRepository listaCancionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CancionService cancionService;

    @Autowired
    private AlbumService albumService;

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

    public boolean addSong(Integer id_lista, Integer id_cancion) {
        Optional<Cancion> optionalCancion = cancionService.getById(id_cancion);
        Optional<ListaCancion> optionalListaCancion = listaCancionRepository.findById(id_lista);
        if(optionalCancion.isPresent() && optionalListaCancion.isPresent()){
            optionalListaCancion.get().addCancion(optionalCancion.get());
            return true;
        }else {
            return false;
        }
    }

    public boolean addAlbum(Integer id_lista, Integer id_album) {
        Optional<Album> optionalAlbum= albumService.getById(id_album);
        Optional<ListaCancion> optionalListaCancion = listaCancionRepository.findById(id_lista);
        if(optionalAlbum.isPresent() && optionalListaCancion.isPresent()){
            List<Cancion> canciones = optionalAlbum.get().getCanciones();
            for(int i = 0;i < canciones.size();i++){
                optionalListaCancion.get().addCancion(canciones.get(i));
            }
            return true;
        }else{
            return false;
        }
    }

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
