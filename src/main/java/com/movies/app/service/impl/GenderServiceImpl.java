package com.movies.app.service.impl;

import com.movies.app.model.Gender;
import com.movies.app.repository.GenderRepository;
import com.movies.app.service.iface.GenderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public List<Gender> getAll() {
        List<Gender> genders = new ArrayList<>();
        // SELECT * FROM gender;
        genderRepository.findAll().forEach(genders::add);
        return genders;
    }

    @Override
    public Gender findById(int id) throws Exception {
        // SELECT * FROM GENDER WHERE gender_id = ?;
        Optional<Gender> gender = genderRepository.findById(id);
        if (!gender.isPresent()) {
            throw new Exception("No existen datos");
        }
        
        return gender.get();
    }

}
