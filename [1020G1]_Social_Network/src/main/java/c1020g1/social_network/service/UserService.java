package c1020g1.social_network.service;

import c1020g1.social_network.model.User;
import c1020g1.social_network.model.dto.UserCreateDTO;


public interface UserService {
    void updateStatus(Integer userId, Integer statusId);

    void updateAvatar(Integer userId, String image);

    void updateBackground(Integer userId, String background);

    User createUser(UserCreateDTO userCreateDTO);

    User getUserById(int id);
}
