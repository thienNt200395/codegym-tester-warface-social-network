package c1020g1.social_network.service;

import c1020g1.social_network.model.GroupUser;

import java.util.List;

public interface GroupUserService {
    List<GroupUser> findAllGroupMember(Integer id);
}
