package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHello() {
        return "Hello from service";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user) {
        User trung = this.userRepository.save(user);
        System.out.println("Run here " + user);
        return trung;
    }

    public User getUserById(long id) {
        User trung = this.userRepository.findById(id);
        return trung;
    }

    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }
}
