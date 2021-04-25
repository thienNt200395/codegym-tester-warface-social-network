package c1020g1.social_network.service;

import c1020g1.social_network.model.Favourite;
import c1020g1.social_network.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteServiceImpl implements FavouriteService{

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Override
    public Iterable<Favourite> getAllFavourite() {
        return favouriteRepository.findAllFavourite();
    }
}
