package com.github.Luythen.Backend;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface FavoriteMealRepository extends CrudRepository<FavoriteMealModel, String> {
    ArrayList<FavoriteMealModel> findAll();
    Optional<FavoriteMealModel> findByFavoriteMealID(String favoriteMealID);
}
