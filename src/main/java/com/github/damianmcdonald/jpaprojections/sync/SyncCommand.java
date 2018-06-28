package com.github.damianmcdonald.jpaprojections.sync;

import java.util.Objects;

public abstract class SyncCommand {

    private final int id;
    private final String className;

    public SyncCommand(final int id, final String className) {
        this.id = id;
        this.className = className;
    }

    public abstract void doSync();

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, className);
    }

    @Override
    public boolean equals(final Object obj){
        if(obj instanceof SyncCommand){
            final SyncCommand other = (SyncCommand) obj;
            return id == other.id
                    && className.equals(other.className);
        } else{
            return false;
        }
    }

}
