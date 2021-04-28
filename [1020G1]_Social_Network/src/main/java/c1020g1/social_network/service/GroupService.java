package c1020g1.social_network.service;

import c1020g1.social_network.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Group findById(int id);
    void remove(Integer id);
    Page<Group> findAllByGroupName(String key,Pageable pageable);
}
