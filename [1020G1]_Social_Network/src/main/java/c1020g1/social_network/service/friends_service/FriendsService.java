package c1020g1.social_network.service.friends_service;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.User;

import java.util.List;

public interface FriendsService {
    List<Friends> getAllFriendByUserId(Integer id);

    List<Object> getAllSuggestFriend(Integer id);

    Friends findFriendsById(Integer id);

    void deleteFriends(Integer id);
}
