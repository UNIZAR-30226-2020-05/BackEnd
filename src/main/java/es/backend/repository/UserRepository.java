package es.backend.repository;

import es.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByNick(String nick);

    Optional<User> findByNickAndContrasena(String nick, String pass);

    @Modifying
    @Query("UPDATE User u set u.contrasena = ?2 where u.id = ?1")
    Integer setUserPasswordById(Integer id, String pass);

    void deleteById(Integer id);

}
