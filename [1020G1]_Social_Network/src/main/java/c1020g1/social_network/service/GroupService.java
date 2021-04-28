package c1020g1.social_network.service;

import c1020g1.social_network.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Page<Group> findAllByGroupName(String key,Pageable pageable);

    Page<Group> findAll(Pageable pageable);

    void save(Group groupSocial);

    Group findById(Integer id);

    void remove(Integer id);
}
