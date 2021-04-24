package c1020g1.social_network.repository;

import c1020g1.social_network.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM favourite")
    Iterable<Favourite> findAllFavourite();
}
