package c1020g1.social_network.service.friends_service.impl;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.friends_repository.FriendsRepository;
import c1020g1.social_network.service.friends_service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;
    @Override
    public List<Friends> getAllFriendByUserId(Integer id) {
        return friendsRepository.getAllFriendByUserId(id);
    }

    @Override
    public List<Object> getAllSuggestFriend(Integer id) {
        return friendsRepository.getAllSuggestFriend(id);
    }

    @Override
    public Friends findFriendsById(Integer id) {
        return friendsRepository.findFriendsById(id);
    }

    @Override
    public void deleteFriends(Integer id) {
        friendsRepository.deleteFriendsByFriendsId(id);
    }
}
