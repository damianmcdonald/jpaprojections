package com.github.damianmcdonald.jpaprojections.dto;

import java.util.List;

public class TeamDto {

    private String name;
    private String location;
    private List<PlayerDto> players;

    public TeamDto(final String name, final String location) {
        this.name = name;
        this.location = location;
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

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

}
