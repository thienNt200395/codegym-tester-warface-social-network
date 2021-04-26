package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.*;
import c1020g1.social_network.model.dto.UserCreateDTO;
import c1020g1.social_network.repository.AccountRepository;
import c1020g1.social_network.repository.FavouriteUserRepository;
import c1020g1.social_network.repository.UserRepository;
import c1020g1.social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FavouriteUserRepository favouriteUserRepository;

    /**
     * author: PhucPT
     * method: transaction invoke account repository, user repository, favourite repository to create account,
     * user and favourites in database. Return user object has been created
     * @param userCreateDTO
     * @return
     */
    @Override
    @Transactional
    public User createUser(UserCreateDTO userCreateDTO) {
        Account account = userCreateDTO.getAccount();
        User user = userCreateDTO.getUser();
        Favourite[] favourites = userCreateDTO.getFavourites();

        accountRepository.createAccount(account);
        Account newAccount = accountRepository.getAccountByName(account.getAccountName());

        user.setAccount(newAccount);
        user.setStatus(new Status(1, "online"));
        userRepository.createUser(user);

        User newUser = userRepository.getUserByAccountId(newAccount.getAccountId());

        for (Favourite favourite : favourites) {
            favouriteUserRepository.createFavouriteUser(favourite, newUser);
        }

        return newUser;
    }

    /**
     * author: PhucPT
     * method: return user in database by id
     * @param id
     * @return
     */
    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }



    @Override
    public void updateStatus(Integer userId, Integer statusId) {
        userRepository.updateStatus( userId, statusId );
    }


    @Override
    public void updateAvatar(Integer userId, String image, String imageName) {
        if (imageName.equals( "png" ) || imageName.equals( "jpg" )) {
            userRepository.updateAvatar( userId, image );
        }
    }

    @Override
    public void updateBackground(Integer userId, String background, String imageName) {
        if (imageName.equals( "png" ) || imageName.equals( "jpg" )) {
            userRepository.updateBackground( userId, background );
        }
    }


}
