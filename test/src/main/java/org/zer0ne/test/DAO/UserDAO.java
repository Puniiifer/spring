package org.zer0ne.test.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zer0ne.test.model.Blog;
import org.zer0ne.test.model.User;
import org.zer0ne.test.repository.UserRepository;

import java.util.List;

@Component
public class UserDAO {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user){
        user.setRole("USER");
        return userRepository.save(user);
    }
    public User getById(int id){
        return userRepository.findById(id).orElse(null);
    }
    public int getId(User user){
        return user.getId();
    }
    public User getUser(User user){
        return userRepository.findByLogin(user.getLogin());
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User updateUser(User user){
        User oldUser = userRepository.findByLogin(user.getLogin());
        oldUser.setLogin(user.getLogin());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone(user.getPhone());
        userRepository.save(oldUser);
        return oldUser;
    }
}
