package es.backend.repository;

import es.backend.model.Album;
import es.backend.model.ListaCancion;
import es.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ListaCancionRepository extends JpaRepository<ListaCancion, Integer> {

    Optional<ListaCancion> findById(Integer id);

    Optional<ListaCancion> findByNombre(String nombre);

    //@Modifying
    //@Query("INSERT INTO Union1 VALUES (?1, ?2, ?3)")
    //Integer addSongToList(Integer idSongList, Integer idSong, java.time.LocalDateTime);

    //esto obtiene los id de las canciones que pertenecen a una lista
    //@Modifying
    //@Query("SELECT id_cancion FROM Union1 WHERE id_listaCancion == ?1")
    //List<Integer> findIdSongByIdSongList(Integer idSongList);



    void deleteById(Integer id);

}
