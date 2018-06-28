package com.github.damianmcdonald.jpaprojections.interceptor;

import com.github.damianmcdonald.jpaprojections.model.Team;
import com.github.damianmcdonald.jpaprojections.sync.SyncCommand;
import com.github.damianmcdonald.jpaprojections.sync.SyncTrack;
import com.github.damianmcdonald.jpaprojections.sync.TeamSyncCommand;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityChangeInterceptor extends EmptyInterceptor {

    private static final Logger LOGGER = Logger.getLogger(EntityChangeInterceptor.class.getName());
    private static final SyncTrack syncTrack = SyncTrack.getInstance();

    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
                                Object[] currentState, Object [] previousState,
                                String[] propertyNames, Type[] types) {
        LOGGER.log(Level.INFO, "Entered method onFlushDirty.");
        if (entity instanceof Team) {
            final Team team = (Team) entity;
            final SyncCommand command = new TeamSyncCommand(team);
            if(!syncTrack.contains(command)) {
                syncTrack.add(command);
            }
        }
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }


    @Override
    public void postFlush(java.util.Iterator entities) {
        LOGGER.log(Level.INFO, "Entered method postFlush.");
        entities.forEachRemaining(entity -> {
            final Team team = (Team) entity;
            final SyncCommand command = new TeamSyncCommand(team);
            syncTrack.notify(command);
        });
    }
}
