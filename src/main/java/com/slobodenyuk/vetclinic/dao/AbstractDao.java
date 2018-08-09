package com.slobodenyuk.vetclinic.dao;

import com.slobodenyuk.vetclinic.entity.AbstractEntity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDao<T extends AbstractEntity> {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public T add(T entity) {
        if (entity.getId() != null) {
            throw new IllegalStateException("Entity for adding can't have an ID already set");
        }
        getCurrentSession().persist(entity);
        getCurrentSession().flush();
        return entity;
    }

    @Transactional
    public T update(T entity) {
        if (entity.getId() == null) {
            throw new IllegalStateException("Entity for updating must have an ID set");
        }
        getCurrentSession().merge(entity);
        getCurrentSession().flush();
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        delete(findOne(id));
    }

    @Transactional
    public void delete(T entity) {
        if (entity.getId() == null) {
            throw new IllegalStateException("Entity for deletion must have an ID set");
        }
        getCurrentSession().delete(entity);
        getCurrentSession().flush();
    }

    @Transactional
    public void deleteAll(List<T> entities) {
        for (T entity : entities) {
            if (entity.getId() == null) {
                throw new IllegalStateException("Entity for deletion must have an ID set");
            }
            getCurrentSession().delete(entity);
            getCurrentSession().flush();
        }
    }

    @Transactional(readOnly = true)
    public T findOne(final Long id) {
        return (T) getCurrentSession().get(getGenericType(), id);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + getGenericType().getSimpleName() + " order by id").list();
    }

    protected abstract Class<T> getGenericType();
}
