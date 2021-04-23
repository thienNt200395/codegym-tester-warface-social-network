package c1020g1.social_network.service.user;

import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer userId) {
        return userRepository.getUserById(userId);
    }
}
