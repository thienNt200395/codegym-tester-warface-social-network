package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.repository.GroupUserRepository;
import c1020g1.social_network.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupUserServiceImpl implements GroupUserService {
    @Autowired
    private GroupUserRepository groupUserRepository;
    @Override
    public Page<GroupUser> findAllByGroupAndUsernameContainingOrderByUsername(int id, String key, Pageable pageable) {
        return groupUserRepository.findAllByGroupAndUsernameContainingOrderByUsername(id,key,pageable);
    }

    @Override
    public void save(GroupUser groupUser) {
        groupUserRepository.save(groupUser);
    }

    @Override
    public void deleteById(int id) {
        groupUserRepository.deleteById(id);
    }

    @Override
    public GroupUser findById(int id) {
        return groupUserRepository.findById(id).orElse(null);
    }

    @Override
    public GroupUser findExist(int groupId,int userId){
        return groupUserRepository.findExist(groupId,userId);
    }

}
