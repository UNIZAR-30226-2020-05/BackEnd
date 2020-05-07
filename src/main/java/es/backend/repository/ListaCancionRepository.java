package es.backend.repository;

import es.backend.model.ListaCancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListaCancionRepository extends JpaRepository<ListaCancion, Integer> {

    Optional<ListaCancion> findByNombre(String nombre);

    void deleteById(Integer id);

}
