package com.github.Luythen.Backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.Luythen.Backend.Dto.FavoriteMealDto;
import com.github.Luythen.Backend.Dto.FavoriteMealsDto;
import com.github.Luythen.Backend.Dto.ResponseDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private FavoriteMealRepository favoriteMealRepository;

    @GetMapping("/initUser")
    public ResponseEntity<?> initalizeNewUser(@CookieValue(name = "userID", required = false) String userID, HttpServletResponse response) {
        ResponseDto responseDto = new ResponseDto();

        if (userID == null) {
            String newID = UUID.randomUUID().toString();
            responseDto.setStatusCode("200");
            responseDto.setMessage("new userID has been assigned");
            ResponseEntity<ResponseDto> rEntity = new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
            
            Cookie cookie = new Cookie("userID", newID);
            cookie.setMaxAge(100000000);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);

            return rEntity;
        }

        responseDto.setStatusCode("200");
        responseDto.setMessage("userID already assigned");
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
    }
    

    @PostMapping("/favorite")
    public String postFavoriteMeal(@CookieValue("userID") String userID, @RequestBody FavoriteMealDto entity) {
        
        try {
            FavoriteMealModel fModel = new FavoriteMealModel();
            fModel.setMealTitle(entity.getMealTitle());
            fModel.setComment(entity.getComment());
            fModel.setMealCategory(entity.getMealCategory());
            fModel.setMealImgSrc(entity.getMealImgSrc());
            fModel.setUserID(userID);
            fModel.setDate(LocalDate.now());
            favoriteMealRepository.save(fModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().toString();
        }
        
        return ResponseEntity.ok().toString();
    }

    @GetMapping("/favorite")
    public ResponseEntity<?> getFavoriteMeals(@CookieValue(name = "userID", required = true) String userID) {
        ResponseDto responseDto = new ResponseDto();
        try {
            FavoriteMealsDto dto = new FavoriteMealsDto();
            ArrayList<FavoriteMealModel> favoriteMealModels = favoriteMealRepository.findAllByUserID(userID);
            if (!favoriteMealModels.isEmpty()) {
                dto.setFavoriteMeals(favoriteMealModels);
                return new ResponseEntity<FavoriteMealsDto>(dto, HttpStatus.CREATED);
            }

            responseDto.setStatusCode("404");
            responseDto.setMessage("Can't find any favories");

            return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseDto.setStatusCode("404");
            responseDto.setMessage("Can't find any favories");
            return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
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
