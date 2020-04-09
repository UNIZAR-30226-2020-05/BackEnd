package es.backend.services;

import es.backend.model.ListaCancion;
import es.backend.model.User;
import es.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListaCancionService listaCancionService;

    public Optional<User> create(User user) {
        ListaCancion listaCancion = new ListaCancion("Favoritos");
        user = userRepository.save(user);
        listaCancionService.create(listaCancion, user);
        return Optional.of(user);
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByNick(String nick) {
        return userRepository.findByNick(nick);
    }

    public Optional<User> getLogin(String nick, String pass) {
        return userRepository.findByNickAndContrasena(nick, pass);
    }

    @Transactional
    public Optional<User> setUserPasswordById(Integer id, String pass) {
        userRepository.setUserPasswordById(id, pass);
        return userRepository.findById(id);
    }

    @Transactional
    public Boolean deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

   /* public boolean sonAmigos(Integer id1, Integer id2) {
        Optional<User> user1 = userRepository.findById(id1);
        Optional<User> user2 = userRepository.findById(id2);
    }
*/
    @Transactional
    public Optional<User> addAmigos(Integer id1, Integer id2) {
        Optional<User> user1 = userRepository.findById(id1);
        Optional<User> user2 = userRepository.findById(id2);

            System.out.println("----------------------");

            System.out.println(user1.get().esAmigo(user2.get()));
            System.out.println(user2.get().esAmigo(user1.get()));
            user1.get().addAmigo(user2.get());
            System.out.println(user1.get().esAmigo(user2.get()));
            System.out.println(user2.get().esAmigo(user1.get()));
            user2.get().addAmigo(user1.get());
            System.out.println(user1.get().esAmigo(user2.get()));
            System.out.println(user2.get().esAmigo(user1.get()));

            System.out.println("----------------------");

            return user1;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
