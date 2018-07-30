package com.github.damianmcdonald.jpaprojections.dto;

import java.util.List;

public class TeamDto {

    private final String name;
    private final String location;
    private List<PlayerDto> players;

    public TeamDto(final String name, final String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

}
