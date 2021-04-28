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
    public User findById(int id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public List<User> inviteFriendList(int groupId, int userId) {
        return userRepository.inviteFriends(groupId, userId);
    }

    @Override
    public List<User> inviteFriendsOfFriendsList(int groupId, int userId) {
        return userRepository.inviteFriendsOfFriends(groupId, userId);
    }
}
