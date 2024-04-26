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
@Table(name = "europeanLeagues")
@Getter
@Setter
@NoArgsConstructor
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

    @Column
    String leagueImagePath;

}
