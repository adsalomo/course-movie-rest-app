package com.movies.app.controller;

import com.movies.app.model.Gender;
import com.movies.app.service.iface.GenderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gender")
@CrossOrigin("*")
public class GenderRestController {
    
    @Autowired
    private GenderService genderService;
    
    @GetMapping
    public List<Gender> getAll() {
        return this.genderService.getAll();
    }
    
    @GetMapping("/findById/{id}")
    public Gender getById(@PathVariable int id) throws Exception {
        return this.genderService.findById(id);
    }
}
