package com.github.damianmcdonald.jpaprojections.dao;

import com.github.damianmcdonald.jpaprojections.dto.PlayerDto;
import com.github.damianmcdonald.jpaprojections.dto.TeamDto;
import com.github.damianmcdonald.jpaprojections.model.Team;

import java.util.List;

public class TeamDao extends EntityDao<Team, TeamDto> {

    public TeamDao() {
        setClazz(Team.class);
    }

    public TeamDto getTeamProjection( int id ) {

        final List<PlayerDto> playersDto = super.entityManager
                .createQuery("select new com.github.damianmcdonald.jpaprojections.dto.PlayerDto(" +
                        "p.firstName, p.surname, p.position, p.rating, s.net, s.charitableContribution" +
                        ") from Player p join p.salary s where p.team.id = :id and p.rating > 92", PlayerDto.class)
                .setParameter("id", id)
                .getResultList();

        final TeamDto teamDto = super.entityManager
                .createQuery("select new com.github.damianmcdonald.jpaprojections.dto.TeamDto(t.name, t.location) from Team t where t.id = :id", TeamDto.class)
                .setParameter("id", id)
                .getSingleResult();

        teamDto.setPlayers(playersDto);

        return teamDto;
    }
}
