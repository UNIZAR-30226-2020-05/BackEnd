package es.backend.services;

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

    public Optional<User> create(User user) {
        return Optional.of(userRepository.save(user));
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

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
