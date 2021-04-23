package c1020g1.social_network.service.user_service.impl;

import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.user_repository.UserRepository;
import c1020g1.social_network.service.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserById(Integer idUser) {
        return userRepository.findUserByUserId(idUser);
    }
}
