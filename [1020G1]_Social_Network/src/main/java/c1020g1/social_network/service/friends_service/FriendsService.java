package c1020g1.social_network.service.friends_service;

import c1020g1.social_network.model.Friends;

import java.util.List;

public interface FriendsService {
    String addNewFriend(Friends friends);
    List<Friends> findAllFriendById(Integer idUser);
}
