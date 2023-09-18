package com.theblog.pikashoot.controllers;


import com.theblog.pikashoot.models.Category;
import com.theblog.pikashoot.models.Tags;
import com.theblog.pikashoot.repositories.CategoryRepository;
import com.theblog.pikashoot.repositories.TagsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tags")
public class TagsController {

    TagsRepository tagsRepository;

    @GetMapping
    public ResponseEntity<List<Tags>> getAllCategories(){
        List<Tags> tags = tagsRepository.findAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("{tagId}")
    public ResponseEntity<Optional<Tags>> getCategoryById(@PathVariable int tagId){
        Optional<Tags> tags = tagsRepository.findById(tagId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
