package com.github.damianmcdonald.jpaprojections;

import com.github.damianmcdonald.jpaprojections.dao.EntityDao;
import com.github.damianmcdonald.jpaprojections.dao.TeamDao;
import com.github.damianmcdonald.jpaprojections.model.Team;
import com.github.damianmcdonald.jpaprojections.sync.SyncAction;
import com.github.damianmcdonald.jpaprojections.sync.SyncTrack;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimulateApp {

    private static SyncTrack syncTrack;
    private static SyncAction syncAction;
    private static EntityDao teamDao;

    @BeforeClass
    public static void getSyncManager() {
        syncAction = syncAction.getInstance();
        syncTrack = SyncTrack.getInstance();
        syncTrack.registerObserver(syncAction);
        teamDao = new TeamDao();
    }

    @Test
    public void simulate() {
        final Team cowboys = getTeamById(2);
        // change the location of the Dallas Cowboys from Dallas to Austin
        cowboys.setLocation("Austin");

        final Team eagles = getTeamById(4);
        // change the location of the Philadelphia Cowboys from Philadelphia to New Jersey
        eagles.setLocation("New Jersey");

        // persist the changes
        saveTeam(cowboys);
        saveTeam(eagles);
    }

    private Team getTeamById(final int id) {
        return (Team) teamDao.findOne(id);
    }

    private void saveTeam(final Team team) {
        teamDao.update(team);
    }
}
