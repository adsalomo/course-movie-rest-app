package com.movies.app.service.iface;

import com.movies.app.model.Gender;
import java.util.List;

public interface GenderService {
    
    List<Gender> getAll();
    
    Gender findById(int id) throws Exception;
}
