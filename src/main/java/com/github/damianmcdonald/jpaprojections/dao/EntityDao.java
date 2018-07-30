package com.github.damianmcdonald.jpaprojections.dao;

import javax.persistence.EntityManager;

public abstract class EntityDao<T1, T2> {

    private EntityManager entityManager = EntityManagerProvider.getInstance().getEntityManager();
    private Class< T1 > clazz1;

    public final void setClazz( Class< T1 > clazz1ToSet ){
        this.clazz1 = clazz1ToSet;
    }

    public T1 findOne( int id ){
        return entityManager.find( clazz1, id );
    }

    public T1 update( T1 entity ){
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    public abstract T2 getDtoProjection( int id );
}
