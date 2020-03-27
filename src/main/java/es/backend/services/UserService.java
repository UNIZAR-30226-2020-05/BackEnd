package es.backend.services;

import es.backend.model.User;
import es.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean create(User user) {
        userRepository.save(user);
        return true;
    }

    public Optional<User> getByNick(String nick) {
        return userRepository.findByNick(nick);
    }

    public Optional<User> getLogin(String nick, String pass) {
        return userRepository.findByNickAndContrasena(nick, pass);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
