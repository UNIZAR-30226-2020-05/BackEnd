package es.backend.services;

import es.backend.model.ListaCancion;
import es.backend.model.Usuario;
import es.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ListaCancionService listaCancionService;

    public Optional<Usuario> create(Usuario usuario) {
        ListaCancion listaCancion = new ListaCancion("Favoritos");
        usuario = usuarioRepository.save(usuario);
        listaCancionService.create(listaCancion, usuario);
        return Optional.of(usuario);
    }

    public Optional<Usuario> getById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> getByNick(String nick) {
        return usuarioRepository.findByNick(nick);
    }

    public Optional<Usuario> getLogin(String nick, String pass) {
        return usuarioRepository.findByNickAndContrasena(nick, pass);
    }

    @Transactional
    public Optional<Usuario> setUserPasswordById(Integer id, String pass) {
        usuarioRepository.setUserPasswordById(id, pass);
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Boolean deleteUser(Integer id) {
        if (usuarioRepository.findById(id).isPresent()) {
            usuarioRepository.deleteById(id);
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
    public Optional<Usuario> addAmigos(Integer id1, Integer id2) {
        Optional<Usuario> user1 = usuarioRepository.findById(id1);
        Optional<Usuario> user2 = usuarioRepository.findById(id2);
        user1.get().addAmigo(user2.get());
        user2.get().addAmigo(user1.get());
        return user1;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

}
