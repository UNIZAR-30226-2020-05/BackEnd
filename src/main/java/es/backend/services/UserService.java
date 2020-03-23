package es.backend.services;

import es.backend.User;
import es.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String create(User user) {
        userRepository.save(user);
        return "Guardado";
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
