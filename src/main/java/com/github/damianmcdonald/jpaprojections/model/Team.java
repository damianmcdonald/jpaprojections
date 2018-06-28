package com.github.damianmcdonald.jpaprojections.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCATION")
    private String location;

    @OneToMany(mappedBy = "team")
    private List<TeamColour> teamColours;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<TeamColour> getTeamColours() {
        return teamColours;
    }

    public void setTeamColours(List<TeamColour> teamColours) {
        this.teamColours = teamColours;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
