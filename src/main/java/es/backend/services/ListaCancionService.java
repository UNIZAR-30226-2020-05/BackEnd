package es.backend.services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import es.backend.model.*;
import es.backend.repository.ListaCancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    public Optional<ListaCancion> createByIdUser(ListaCancion listaCancion, Integer idUser) {
        Optional<Usuario> usuario = userService.getById(idUser);
        if (usuario.isPresent()) {
            listaCancion.setUsuario(usuario.get());
            return Optional.of(listaCancionRepository.save(listaCancion));
        }
        return Optional.empty();
    }

    public Optional<ListaCancion> create(ListaCancion listaCancion, Usuario usuario) {
        listaCancion.setUsuario(usuario);
        return Optional.of(listaCancionRepository.save(listaCancion));
    }

    public Optional<ListaCancion> getById(Integer id) {
        return listaCancionRepository.findById(id);
    }

    @Transactional
    public Optional<ListaCancion> addSong(Integer id_lista, Integer id_cancion, AtomicBoolean existeCancion) {
        Optional<Cancion> optionalCancion = cancionService.getById(id_cancion);
        Optional<ListaCancion> optionalListaCancion = listaCancionRepository.findById(id_lista);
        if(optionalCancion.isPresent() && optionalListaCancion.isPresent()){
            if(optionalListaCancion.get().existeCancion(optionalCancion.get())){
                existeCancion.set(true);
            }else {
                optionalListaCancion.get().addCancion(optionalCancion.get());
                existeCancion.set(false);
            }
            return optionalListaCancion;
        } else {
            return optionalListaCancion.empty();
        }
    }

    @Transactional
    public Optional<ListaCancion> addAlbum(Integer id_lista, Integer id_album, AtomicInteger existeCancion) {
        Optional<Album> optionalAlbum= albumService.getById(id_album);
        Optional<ListaCancion> optionalListaCancion = listaCancionRepository.findById(id_lista);
        int yaEnLista = 0;
        if(optionalAlbum.isPresent() && optionalListaCancion.isPresent()){
            existeCancion.set(0);
            List<Cancion> canciones = optionalAlbum.get().getCanciones();
            for(int i = 0;i < canciones.size();i++){
                if(!optionalListaCancion.get().existeCancion(canciones.get(i))){
                    optionalListaCancion.get().addCancion(canciones.get(i));
                }else{
                    yaEnLista++;
                }
            }
            if(yaEnLista == canciones.size()){
                existeCancion.set(2);
            }else if(yaEnLista > 0){
                existeCancion.set(1);
            }
            return optionalListaCancion;
        }else{
            return optionalListaCancion.empty();
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

    @Transactional
    public Optional<ListaCancion> deleteCancionDeLista(Integer id_lista, Integer id_cancion) {
        Optional<Cancion> optionalCancion = cancionService.getById(id_cancion);
        Optional<ListaCancion> optionalListaCancion = listaCancionRepository.findById(id_lista);

        if(optionalCancion.isPresent() && optionalListaCancion.isPresent()){
            optionalListaCancion.get().deleteCancion(optionalCancion.get());
            return optionalListaCancion;
        } else {
            return optionalListaCancion;
        }
    }
}
