package com.github.Luythen.Backend;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface FavoriteMealRepository extends CrudRepository<FavoriteMealModel, String> {
    ArrayList<FavoriteMealModel> findAllByUserID(String userID);
    Optional<FavoriteMealModel> findByFavoriteMealID(String favoriteMealID);
}
