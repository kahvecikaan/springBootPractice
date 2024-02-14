package com.project.rentACar.dataAccess.abstracts;

import com.project.rentACar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BrandRepository extends JpaRepository<Brand, Integer>{
    boolean existsByName(String name);

}
