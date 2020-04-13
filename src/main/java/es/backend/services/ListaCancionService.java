package es.backend.services;

import es.backend.model.*;
import es.backend.repository.AlbumRepository;
import es.backend.repository.ListaCancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public Optional<ListaCancion> addSong(Integer id_lista, Integer id_cancion) {
        Optional<Cancion> optionalCancion = cancionService.getById(id_cancion);
        Optional<ListaCancion> optionalListaCancion = listaCancionRepository.findById(id_lista);
        System.out.println("Lista:" + optionalListaCancion.get().getCanciones().stream().map(Cancion::getNombre)
                .collect(Collectors.toList()));
        System.out.println(optionalCancion.get().getNombre());
        if(optionalCancion.isPresent() && optionalListaCancion.isPresent()){
            optionalListaCancion.get().addCancion(optionalCancion.get());
            return optionalListaCancion;
        } else {
            return optionalListaCancion;
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
