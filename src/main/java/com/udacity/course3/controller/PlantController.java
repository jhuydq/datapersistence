package com.udacity.course3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.course3.entity.delivery.Delivery;
import com.udacity.course3.entity.inventory.Plant;
import com.udacity.course3.service.PlantService;
import com.udacity.course3.view.Views;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    PlantService plantService;

    public PlantDTO getPlantDTO(String name){
        Plant plant = plantService.getPlantByName(name);
        return convertToPlantDTO(plant);
    }

    @PostMapping
    public Long insertPlant(@RequestBody PlantDTO plantDTO) {
        return plantService.save(convertToPlant(plantDTO));
    }

    @GetMapping("/{plantId}")
    public boolean isDelivered(@PathVariable Long plantId){
        return plantService.isDelivered(plantId);
    }

    @GetMapping("/plants/{price}")
    public List<PlantDTO> getPlantsByPriceLessThan(@PathVariable BigDecimal price){
        List<Plant> plants = plantService.getPlantsByPriceLessThan(price);
        List<PlantDTO> plantDTOs = new ArrayList<>();
        for (Plant plant : plants) {
            plantDTOs.add(convertToPlantDTO(plant));
        }
        return plantDTOs;
    }

    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.getPlantByName(name);
    }

    private PlantDTO convertToPlantDTO(Plant plant){
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }

    private Plant convertToPlant(PlantDTO plantDTO){
        Plant plant = new Plant();
        BeanUtils.copyProperties(plantDTO, plant);
        return plant;
    }


}
