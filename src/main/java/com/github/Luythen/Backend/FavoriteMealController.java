package com.github.Luythen.Backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.Luythen.Backend.Dto.FavoriteMealDto;
import com.github.Luythen.Backend.Dto.FavoriteMealsDto;
import com.github.Luythen.Backend.Dto.ResponseDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


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
            fModel.setMealTitle(entity.getMealTitle());
            fModel.setComment(entity.getComment());
            fModel.setMealCategory(entity.getMealCategory());
            fModel.setMealImgSrc(entity.getMealImgSrc());
            fModel.setUserID(entity.getUserID());
            fModel.setDate(LocalDate.now());
            favoriteMealRepository.save(fModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().toString();
        }
        
        return ResponseEntity.ok().toString();
    }

    @GetMapping("/favorite")
    public ResponseEntity<?> getFavoriteMeals(@RequestParam("userID") String userID) {
        try {
            FavoriteMealsDto dto = new FavoriteMealsDto();
            ArrayList<FavoriteMealModel> favoriteMealModels = favoriteMealRepository.findAllByUserID(userID);
            if (!favoriteMealModels.isEmpty()) {
                dto.setFavoriteMeals(favoriteMealModels);
                return new ResponseEntity<FavoriteMealsDto>(dto, HttpStatus.CREATED);
            }

            return new ResponseEntity<String>("Bad request", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/favorite")
    public ResponseEntity<?> editFavoriteMeal(@RequestBody FavoriteMealDto entity) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Optional<FavoriteMealModel> fModel = favoriteMealRepository.findByFavoriteMealID(entity.getFavoriteMealID());
            if (!fModel.isEmpty()) {
                fModel.get().setComment(entity.getComment());
                favoriteMealRepository.save(fModel.get());
            }
        } catch (Exception e) {
            responseDto.setStatusCode("404");
            responseDto.setMessage("Bad request");
            return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
        }
        responseDto.setStatusCode("200");
        responseDto.setMessage("Update favorite meal comment");
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

}
