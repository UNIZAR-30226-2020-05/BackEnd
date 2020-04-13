package es.backend.repository;

import es.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNick(String nick);

    Optional<Usuario> findByNickAndContrasena(String nick, String pass);

    @Modifying
    @Query("UPDATE Usuario u set u.contrasena = ?2 where u.id = ?1")
    Integer setUserPasswordById(Integer id, String pass);

    void deleteById(Integer id);

}
