package com.project.rentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="models")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    @Column(name="id")
    private int id;

    private String name;

    @ManyToOne // A brand can have many models
    @JoinColumn(name="brand_id") // Model table (owning side) will have a FK pointing to Brand table with 'brand_id' in Model table
    private Brand brand;

    @OneToMany(mappedBy = "model") // Cars table (owning side) will have a FK pointing to Model table with 'model' in Cars table
    private List<Car> cars;
}
