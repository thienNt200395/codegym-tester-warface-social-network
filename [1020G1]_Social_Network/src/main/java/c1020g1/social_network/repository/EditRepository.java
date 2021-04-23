package c1020g1.social_network.repository;

import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EditRepository extends JpaRepository<User, Integer> {
}
