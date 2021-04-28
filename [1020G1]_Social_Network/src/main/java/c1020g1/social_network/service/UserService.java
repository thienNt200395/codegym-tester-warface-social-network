package c1020g1.social_network.service;

import c1020g1.social_network.model.User;
import c1020g1.social_network.model.dto.UserCreateDTO;

import java.util.List;

public interface UserService {
    List<User> inviteFriendList(int groupId,int userId);
    List<User> inviteFriendsOfFriendsList(int groupId,int userId);

    User createUser(UserCreateDTO userCreateDTO);

    User getUserById(Integer id);
//dương
    User getUserByEmail(String email);
}
