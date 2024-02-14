package com.project.rentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="plate")
    private String plate;

    @Column(name="daily_price")
    private double dailyPrice;

    @Column(name="model_year")
    private int modelYear;

    @Column(name="state")
    private int state; // 0: Available, 1: Rented, 2: Maintenance

    @ManyToOne // A model can have many cars
    @JoinColumn(name="model_id")
    private Model model;
}
