package com.github.damianmcdonald.jpaprojections.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.damianmcdonald.jpaprojections.dao.EntityDao;
import com.github.damianmcdonald.jpaprojections.dao.TeamDao;
import com.github.damianmcdonald.jpaprojections.dto.TeamDto;
import com.github.damianmcdonald.jpaprojections.model.Team;

import java.util.logging.Logger;


public final class TeamSyncCommand extends SyncCommand {

    private static final Logger LOGGER = Logger.getLogger(TeamSyncCommand.class.getName());
    private EntityDao teamDao;

    public TeamSyncCommand(Team team) {
        super(team.getId(), team.getClass().getSimpleName());
        teamDao = new TeamDao();
    }

    @Override
    public void doSync() {
        try {
            final String jsonOutput = new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString((TeamDto) teamDao.getTeamProjection(getId()));
            LOGGER.info(String.format("Syncing TEAM entity %s", jsonOutput));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}