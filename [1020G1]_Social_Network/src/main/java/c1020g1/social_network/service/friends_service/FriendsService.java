package c1020g1.social_network.service.friends_service;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.SuggestFriend;

import java.util.List;

public interface FriendsService {
    List<SuggestFriend> getAllSuggestFriend(Integer id);

    Friends findFriendsById(Integer id);

    void deleteFriends(Integer id);

    List<Friends> findAllFriendById(Integer idUser);

    String addNewFriend(Friends friends);
}
