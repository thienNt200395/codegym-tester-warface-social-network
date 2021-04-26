package c1020g1.social_network.service;

import c1020g1.social_network.model.User;
import c1020g1.social_network.model.dto.UserCreateDTO;

import java.util.List;

public interface UserService {

    User createUser(UserCreateDTO userCreateDTO);

    User getUserById(Integer id);
//dương
    User getUserByEmail(String email);

    List<User> inviteFriendList(int groupId, int userId);
    List<User> inviteFriendsOfFriendsList(int groupId);
}
