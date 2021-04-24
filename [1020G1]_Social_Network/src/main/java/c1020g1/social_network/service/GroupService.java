package c1020g1.social_network.service;

import c1020g1.social_network.model.Group;

import java.util.List;

public interface GroupService {
    List<Group> findAll();

    List<Group> findGroupByNameContaining(String name);

    void save(Group groupSocial);

    Group findById(Integer id);

    void remove(Integer id);
}
