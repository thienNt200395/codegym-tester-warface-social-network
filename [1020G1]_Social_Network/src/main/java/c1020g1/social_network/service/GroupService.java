package c1020g1.social_network.service;

import c1020g1.social_network.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService {
    Page<Group> findAll(Pageable pageable);

    List<Group> findGroupByNameContaining(String name);

    void save(Group groupSocial);

    Group findById(Integer id);

    void remove(Integer id);
}
