package com.theblog.pikashoot.controllers;


import com.theblog.pikashoot.models.Category;
import com.theblog.pikashoot.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {

    CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> category = categoryRepository.findAll();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("{catId}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable int catId){
        Optional<Category> category = categoryRepository.findById(catId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
