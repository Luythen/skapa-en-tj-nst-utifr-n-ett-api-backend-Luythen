package com.github.Luythen.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin("http://localhost:3030")
@RestController
@RequestMapping("/api")
public class FavoriteMealController {

    @Autowired
    private FavoriteMealRepository favoriteMealRepository;

    @PostMapping("/favorite")
    public String postFavoriteMeal(@RequestBody FavoriteMealDto entity) {
        
        try {
            FavoriteMealModel fModel = new FavoriteMealModel();
            fModel.setMealID(entity.getMealID());
            fModel.setMealCategory(entity.getMealCategory());
            fModel.setUserID(entity.getUserID());
            favoriteMealRepository.save(fModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().toString();
        }
        
        return ResponseEntity.ok().toString();
    }
    

}
