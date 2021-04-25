package c1020g1.social_network.service;

import c1020g1.social_network.model.User;
<<<<<<< HEAD

import java.util.List;

public interface UserService {
    User findById(int id);
    List<User> inviteFriendList(int groupId,int userId);
    List<User> inviteFriendsOfFriendsList(int groupId);
=======
import c1020g1.social_network.model.dto.UserCreateDTO;

public interface UserService {

    User createUser(UserCreateDTO userCreateDTO);

    User getUserById(Integer id);
//dương
    User getUserByEmail(String email);
>>>>>>> dev
}
