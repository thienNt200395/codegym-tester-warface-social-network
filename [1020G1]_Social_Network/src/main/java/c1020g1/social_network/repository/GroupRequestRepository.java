package c1020g1.social_network.repository;

import c1020g1.social_network.model.GroupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRequestRepository extends JpaRepository<GroupRequest,Integer> {
}
