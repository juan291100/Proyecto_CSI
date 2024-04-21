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
@Table(name = "europeanPlayers")
@Getter
@Setter
public class PlayersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPlayer;

    @Column(nullable = false)
    Long idTeam;

    @Column(nullable = false)
    String namePlayer;

    @Column(nullable = false)
    String lastNamePlayer;

    @Column
    int agePlayer;

    @Column(nullable = false)
    String positionPlayer;

    @Column 
    int squadNumberPlayer;

    public PlayersModel(Long idTeam, String namePlayer, String lastNamePlayer, int agePlayer, 
        String positionPlayer, int squadNumberPlayer) {
        this.idTeam = idTeam;
        this.namePlayer = namePlayer;
        this.lastNamePlayer = lastNamePlayer;
        this.agePlayer = agePlayer;
        this.positionPlayer = positionPlayer;
        this.squadNumberPlayer = squadNumberPlayer;
    }

}
