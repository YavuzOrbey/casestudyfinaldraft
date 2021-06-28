package com.casestudydraft.service;


import com.casestudydraft.model.Measurement;
import com.casestudydraft.model.User;
import com.casestudydraft.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;
    //autowire the constructor for testing purposes

    public void save(Measurement measurement){
        measurement.setUpdated_on(new Timestamp(System.currentTimeMillis()));
        measurementRepository.save(measurement);
    }
    public List<Measurement> listAll() {
        return measurementRepository.findAll();
    }

    public Measurement get(Long id){
        return measurementRepository.getById(id);
    }
    public Measurement findByName(String name){
        return measurementRepository.findByName(name);
    }

    public Measurement findByNameIgnoreCaseContaining(String name){
        return measurementRepository.findByNameIgnoreCaseContaining(name);
    }
    public void delete(Measurement measurement){
        measurementRepository.delete(measurement);
    }
    public void deleteById(Long id) {
        measurementRepository.deleteById(id);
    }


}
