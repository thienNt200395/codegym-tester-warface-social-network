package c1020g1.social_network.service.friends_service;

import c1020g1.social_network.model.Friends;

import java.util.List;

public interface FriendsService {
    List<Object> getAllSuggestFriend(Integer id);

    Friends findFriendsById(Integer id);

    void deleteFriends(Integer id);
    List<Friends> findAllFriendById(Integer idUser);
    String addNewFriend(Friends friends);
}
