package com.udacity.course3.repo;

import com.udacity.course3.entity.inventory.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    @Query("SELECT p.delivery.isCompleted FROM Plant p WHERE p.id = :plantId")
    public boolean isDelivered(Long plantId);

    public List<Plant> findPlantsByPriceLessThan(BigDecimal price);
    public Plant findPlantByName(String name);
}
