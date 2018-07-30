package com.github.damianmcdonald.jpaprojections.dao;

import com.github.damianmcdonald.jpaprojections.sync.SyncTrack;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Logger;

public class EntityManagerProvider {

    private static final Logger LOGGER = Logger.getLogger(EntityManagerProvider.class.getName());
    private static EntityManagerProvider instance;
    private static EntityManagerFactory emf;
    private static EntityManager entityManager;

    private EntityManagerProvider(){}

    public static EntityManagerProvider getInstance(){
        if(instance == null){
            synchronized (SyncTrack.class) {
                if (instance == null){
                    instance = new EntityManagerProvider();
                    if(emf == null) {
                        emf = Persistence.createEntityManagerFactory("entity-jpa");
                        if(entityManager == null) {
                            entityManager = emf.createEntityManager();
                        }
                    }
                }
            }
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
