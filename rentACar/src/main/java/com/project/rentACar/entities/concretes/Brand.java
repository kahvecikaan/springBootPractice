package com.project.rentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brand {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "brand") // Model table (owning side) will have a FK pointing to Brand table with 'brand' in Model table
    private List<Model> models;
}
