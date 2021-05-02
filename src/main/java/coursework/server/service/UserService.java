package coursework.server.service;

import coursework.server.entity.User;
import coursework.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(User user){
        userRepository.saveAndFlush(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User find(Long id){ return userRepository.getOne(id); }
}
