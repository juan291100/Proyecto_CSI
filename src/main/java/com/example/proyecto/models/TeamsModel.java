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
    Long idTeam;

    @Column(nullable = false)
    Long idLeague;

    @Column(nullable = false,unique = true)
    String nameTeam;

    @Column(nullable = false)
    int yearTeam;

    @Column(nullable = false)
    String cityTeam;

    @Column
    int trophiesTeam;

    public TeamsModel(Long idLeague, String nameTeam, int yearTeam, String cityTeam, int trophiesTeam) {
        this.idLeague = idLeague;
        this.nameTeam = nameTeam;
        this.yearTeam = yearTeam;
        this.cityTeam = cityTeam;
        this.trophiesTeam = trophiesTeam;
    }
    
}
