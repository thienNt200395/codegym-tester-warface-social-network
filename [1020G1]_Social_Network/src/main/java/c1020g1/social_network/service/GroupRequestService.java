package c1020g1.social_network.service;

import c1020g1.social_network.model.GroupRequest;
import c1020g1.social_network.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupRequestService {
    GroupRequest findById(int id);
    void deleteById(int id);
    Page<GroupRequest> findAllByGroupAndKey(int groupId,String key, Pageable pageable);
    List<GroupRequest> findAllByUser(User user);
    GroupRequest findExist(GroupRequest groupRequest);
    void save(GroupRequest groupRequest);
}
