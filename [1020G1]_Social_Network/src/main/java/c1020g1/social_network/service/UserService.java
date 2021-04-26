package c1020g1.social_network.service;

import c1020g1.social_network.model.User;
import c1020g1.social_network.model.dto.UserCreateDTO;


public interface UserService {
    //HanTH
    void updateStatus(Integer userId, Integer statusId);
    //HanTH
    void updateAvatar(Integer userId, String image, String imageName);
    //HanTH
    void updateBackground(Integer userId, String background, String imageName);
    //PhucPT
    User createUser(UserCreateDTO userCreateDTO);
    //PhucPT
    User getUserById(int id);
}
