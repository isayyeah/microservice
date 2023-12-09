package com.example.voiture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Voiture {
    @Id
    @GeneratedValue
    private Long id;
    private String matricule;
    private String marque;
    private String model;
    @Column(name = "id_client")
    private Long clientId;


}
