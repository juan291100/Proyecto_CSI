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
    Long leagueId;

    @Column(nullable = false,unique = true)
    String leagueName;

    @Column(nullable = false)
    String leagueCountry;

    @Column
    String leagueAssociation;

    public LeaguesModel(String leagueName, String leagueCountry, String leagueAssociation) {
        this.leagueName = leagueName;
        this.leagueCountry = leagueCountry;
        this.leagueAssociation = leagueAssociation;
    }
   
}
