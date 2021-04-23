package c1020g1.social_network.service;

import c1020g1.social_network.model.GroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface GroupUserService {
    Page<GroupUser> findAllByGroupAndUsernameContainingOrderByUsername(int id, String key, Pageable pageable);
    void save(GroupUser groupUser);
    void deleteById(int id);
    GroupUser findById(int id);
    GroupUser findExist(int groupId, int userId);
}
