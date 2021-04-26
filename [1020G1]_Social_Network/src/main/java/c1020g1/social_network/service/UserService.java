package c1020g1.social_network.service;

import c1020g1.social_network.model.User;

import java.util.List;

public interface UserService {
    void updateStatus(Integer userId, Integer statusId);

    User findUserById(Integer id);

    void updateAvatar(Integer userId, String image, String imageName);

    void updateBackground(Integer userId, String background, String imageName);
}
