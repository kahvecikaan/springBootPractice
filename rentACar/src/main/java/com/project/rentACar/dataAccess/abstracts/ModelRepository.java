package com.project.rentACar.dataAccess.abstracts;

import com.project.rentACar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByNameAndBrandId(String name, int brandId);
}
