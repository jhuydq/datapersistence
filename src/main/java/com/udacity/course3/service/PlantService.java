package com.udacity.course3.service;

import com.udacity.course3.entity.inventory.Plant;
import com.udacity.course3.repo.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    private PlantRepository plantRepository;

    public Plant getPlantByName(String name){
        return plantRepository.findPlantByName(name);
    }

    public Boolean isDelivered(Long plantId){
        return plantRepository.isDelivered(plantId);
    }

    public List<Plant> getPlantsByPriceLessThan(BigDecimal price){
        return plantRepository.findPlantsByPriceLessThan(price);
    }

    public Long save(Plant plant){
        return plantRepository.save(plant).getId();
    }
}
