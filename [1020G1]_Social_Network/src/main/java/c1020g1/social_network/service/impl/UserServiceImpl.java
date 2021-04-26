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

    /**
     * method: update status of user.
     * author: HanTH.
     *
     * @param userId
     * @param statusId
     */
    @Override
    public void updateStatus(Integer userId, Integer statusId) {
        userRepository.updateStatus( userId, statusId );
    }

    /**
     * method: find user by id of user.
     * author: HanTH.
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById( id );
    }

    /**
     * method: update avatar of user.
     *
     * @param userId
     * @param image
     * @param imageName
     */
    @Override
    public void updateAvatar(Integer userId, String image, String imageName) {
        if (imageName.equals( "png" ) || imageName.equals( "jpg" )) {
            userRepository.updateAvatar( userId, image );
        }
    }

    /**
     * method: update background of user.
     *
     * @param userId
     * @param background
     * @param imageName
     */
    @Override
    public void updateBackground(Integer userId, String background, String imageName) {
        if (imageName.equals( "png" ) || imageName.equals( "jpg" )) {
            userRepository.updateBackground( userId, background );
        }
    }
}
