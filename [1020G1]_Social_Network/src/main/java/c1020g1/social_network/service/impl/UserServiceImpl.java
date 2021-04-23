package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.UserRepository;
import c1020g1.social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateStatus(Integer userId, Integer statusId) {
        userRepository.updateStatus( userId, statusId );
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById( id );
    }

    @Override
    public void updateAvatar(Integer userId, String image) {
        userRepository.updateAvatar( userId, image );
    }

    @Override
    public void updateBackground(Integer userId, String background) {
        userRepository.updateBackground( userId,background );
    }
}
