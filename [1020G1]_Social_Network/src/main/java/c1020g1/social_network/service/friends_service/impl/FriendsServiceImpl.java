package c1020g1.social_network.service.friends_service.impl;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.repository.friends_repository.FriendsRepository;
import c1020g1.social_network.service.friends_service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    FriendsRepository friendsRepository;

    @Override
    public String addNewFriend(Friends friends) {
        //Check list friend of user has this friend or not.
        List<Friends> friendsList = friendsRepository.findAllFriendById(friends.getUser().getUserId());
        for (Friends friends1 : friendsList){
            if (friends1.getFriend().getUserId() == friends.getFriend().getUserId())
                return "NG";
        }
        friendsRepository.save(friends);
        return "OK";
    }

    @Override
    public List<Friends> findAllFriendById(Integer idUser) {
        return friendsRepository.findAllFriendById(idUser);
    }

}
