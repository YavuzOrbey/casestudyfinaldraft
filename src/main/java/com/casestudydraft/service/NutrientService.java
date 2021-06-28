package com.casestudydraft.service;



import com.casestudydraft.model.Nutrient;

import com.casestudydraft.repository.NutrientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class NutrientService  {
    @Autowired
    NutrientRepository nutrientRepository;

    public void save(Nutrient nutrient){
        nutrient.setUpdated_on(new Timestamp(System.currentTimeMillis()));
        nutrientRepository.save(nutrient);
    }
    public List<Nutrient> findAll() {
        return nutrientRepository.findAll();
    }

    public Nutrient get(Long id){
        return nutrientRepository.findById(id).get();
    }
    public Nutrient findByName(String name){
        return nutrientRepository.findByName(name);
    }
    public List<Nutrient> findByNameIgnoreCaseContaining(String name) { return nutrientRepository.findByNameIgnoreCaseContaining(name);}
    public void delete(Nutrient nutrient){
        nutrientRepository.delete(nutrient);
    }


}
