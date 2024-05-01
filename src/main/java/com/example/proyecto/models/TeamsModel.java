package com.example.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "europeanTeams")
@Getter
@Setter
@NoArgsConstructor
/**
 * La clase TeamsModel en una entidad la cual se encarga de representar  
 * los datos referentes a los equipos de fútbol de la base de datos.
 */
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

    @Column
    String teamImagePath;

    public TeamsModel(Long leagueId) {
        this.leagueId = leagueId;
    }
    
}
