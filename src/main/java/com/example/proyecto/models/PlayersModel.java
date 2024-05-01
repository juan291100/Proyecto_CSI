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
@Table(name = "europeanPlayers")
@Getter
@Setter
@NoArgsConstructor
/**
 * La clase PlayersModel en una entidad la cual se encarga de representar  
 * los datos referentes a los jugadores de fútbol de la base de datos.
 */
public class PlayersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long playerId;

    @Column(nullable = false)
    Long teamId;

    @Column(nullable = false)
    String playerName;

    @Column(nullable = false)
    String playerLastName;

    @Column
    int playerAge;

    @Column(nullable = false)
    String playerPosition;

    @Column 
    int playerSquadNumber;

    public PlayersModel(Long teamId) {
        this.teamId = teamId;
    }

    public PlayersModel(Long teamId, String playerName, String playerLastName, int playerAge, String playerPosition,
            int playerSquadNumber) {
        this.teamId = teamId;
        this.playerName = playerName;
        this.playerLastName = playerLastName;
        this.playerAge = playerAge;
        this.playerPosition = playerPosition;
        this.playerSquadNumber = playerSquadNumber;
        }

}
