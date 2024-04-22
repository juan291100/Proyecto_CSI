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
@Table(name = "europeanTeams")
@Getter
@Setter
public class TeamsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long teamId;

    @Column(nullable = false)
    Long leagueId;

    @Column(nullable = false,unique = true)
    String teamName;

    @Column(nullable = false)
    int teamYear;

    @Column(nullable = false)
    String teamCity;

    @Column
    int teamTrophies;

    public TeamsModel(Long leagueId, String teamName, int teamYear, String teamCity, int teamTrophies) {
        this.leagueId = leagueId;
        this.teamName = teamName;
        this.teamYear = teamYear;
        this.teamCity = teamCity;
        this.teamTrophies = teamTrophies;
    }
    
}
