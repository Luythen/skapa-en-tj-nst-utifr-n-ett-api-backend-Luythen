package com.github.Luythen.Backend;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.Luythen.Backend.Dto.FavoriteMealDto;
import com.github.Luythen.Backend.Dto.FavoriteMealsDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

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
            fModel.setComment(entity.getComment());
            fModel.setMealCategory(entity.getMealCategory());
            fModel.setUserID(entity.getUserID());
            fModel.setDate(LocalDate.now());
            favoriteMealRepository.save(fModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().toString();
        }
        
        return ResponseEntity.ok().toString();
    }

    @GetMapping("/favorite")
    public ResponseEntity<?> getFavoriteMeals(@RequestBody FavoriteMealDto entity) {
        try {
            FavoriteMealsDto dto = new FavoriteMealsDto();
            ArrayList<FavoriteMealModel> favoriteMealModels = favoriteMealRepository.findAllByUserID(entity.getUserID());
            if (!favoriteMealModels.isEmpty()) {
                dto.setFavoriteMeals(favoriteMealModels);
                return new ResponseEntity<FavoriteMealsDto>(dto, HttpStatus.CREATED);
            }

            return new ResponseEntity<String>("Bad request", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    

}
