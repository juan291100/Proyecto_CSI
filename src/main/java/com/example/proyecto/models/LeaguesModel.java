package com.example.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "europeanLeagues")
@Getter
@Setter
public class LeaguesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLeague;

    @Column(nullable = false,unique = true)
    String nameLeague;

    @Column(nullable = false)
    String countryLeague;

    @Column
    String associationLeague;

    public LeaguesModel(String nameLeague, String countryLeague, String associationLeague) {
        this.nameLeague = nameLeague;
        this.countryLeague = countryLeague;
        this.associationLeague = associationLeague;
    }
   
}
