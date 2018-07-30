package com.github.damianmcdonald.jpaprojections.sync;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class SyncTrack extends Observable {

    private static SyncTrack instance;

    private SyncTrack(){}

    // Java 8 hack to pbtain a concurrent Set
    // see https://javarevisited.blogspot.com/2017/08/how-to-create-thread-safe-concurrent-hashset-in-java-8.html
    private final static ConcurrentHashMap<SyncCommand, String> SYNCABLE_MAP = new ConcurrentHashMap<SyncCommand, String>();
    private final static Set<SyncCommand> SYNCABLE_ITEMS = SYNCABLE_MAP.newKeySet();

    public static SyncTrack getInstance(){
        if(instance == null){
            synchronized (SyncTrack.class) {
                if (instance == null){
                    instance = new SyncTrack();
                }
            }
        }
        return instance;
    }

    public boolean add(final SyncCommand command) {
        return SYNCABLE_ITEMS.add(command);
    }

    public void notify(SyncCommand command) {
        if(contains(command)) {
            setChanged();
            notifyObservers(command);
        }
    }

    public boolean contains(final SyncCommand command) {
        return SYNCABLE_ITEMS.contains(command);
    }

    public boolean remove(final SyncCommand command) {
        return SYNCABLE_ITEMS.remove(command);
    }

    public void registerObserver(final Observer observer) {
        super.addObserver(observer);
    }

}
