package com.github.damianmcdonald.jpaprojections.sync;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SyncAction implements Observer {

    private static final Logger LOGGER = Logger.getLogger(SyncAction.class.getName());
    private static final SyncTrack syncTrack = SyncTrack.getInstance();
    private static SyncAction instance;

    private SyncAction(){}

    public static SyncAction getInstance(){
        if(instance == null){
            synchronized (SyncTrack.class) {
                if (instance == null){
                    instance = new SyncAction();
                }
            }
        }
        return instance;
    }

    @Override
    public void update(final Observable o, final Object syncCommand) {
        LOGGER.log(Level.INFO, String.format("Entered update method"));
        final SyncCommand command = (SyncCommand) syncCommand;
        try {
            command.doSync();
            // if the sync operations executes successfully then remove the command from the SyncTrack
            syncTrack.remove(command);
        } catch (Exception ex) {
            ex.printStackTrace();
            // if the sync operation fails, handle the failure
            LOGGER.log(Level.SEVERE,
                    String.format("Sync operation failed for command type %s with id %d and className %s",
                            command.getId(), command.getClassName())
            );
        }
    }

}
