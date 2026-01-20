package com.github.Luythen.Backend;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface FavoriteMealRepository extends CrudRepository<FavoriteMealModel, UUID> {
    ArrayList<FavoriteMealModel> findAll();
    Optional<FavoriteMealModel> findByFavoriteMealID();
}
