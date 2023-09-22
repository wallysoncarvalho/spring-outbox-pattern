package com.sandbox.springoutboxpattern

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.hibernate.Session
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class HibernateCustomRepository<T>(
    @PersistenceContext
    private val entityManager: EntityManager
) : CustomRepository<T> {

    @Transactional
    override fun <S : T> updateAllAndFlush(entities: Iterable<S>): List<S> {
        val result: MutableList<S> = ArrayList()
        for (entity in entities) {
            result.add(update(entity))
        }
        entityManager.flush()
        return result
    }

    @Transactional
    override fun <S : T> updateAll(entities: Iterable<S>): List<S> {
        val result: MutableList<S> = ArrayList()
        for (entity in entities) {
            result.add(update(entity))
        }
        return result
    }

    @Transactional
    override fun <S : T> updateAndFlush(entity: S): S {
        update(entity)
        entityManager.flush()
        return entity
    }

    @Transactional
    override fun <S : T> update(entity: S): S {
        session().merge(entity)
        return entity
    }

    @Transactional
    override fun <S : T> mergeAllAndFlush(entities: Iterable<S>): List<S> {
        val result: MutableList<S> = ArrayList()
        for (entity in entities) {
            result.add(merge(entity))
        }
        entityManager.flush()
        return result
    }

    @Transactional
    override fun <S : T> mergeAll(entities: Iterable<S>): List<S> {
        val result: MutableList<S> = ArrayList()
        for (entity in entities) {
            result.add(merge(entity))
        }
        return result
    }

    @Transactional
    override fun <S : T> mergeAndFlush(entity: S): S {
        merge(entity)
        entityManager.flush()
        return entity
    }

    @Transactional
    override fun <S : T> merge(entity: S): S {
        session().merge(entity)
        return entity
    }

    @Transactional
    override fun <S : T> persistAllAndFlush(entities: Iterable<S>): List<S> {
        val result: MutableList<S> = ArrayList()
        for (entity in entities) {
            result.add(persist(entity))
        }
        entityManager.flush()
        return result

    }

    @Transactional
    override fun <S : T> persistAll(entities: Iterable<S>): List<S> {
        val result: MutableList<S> = ArrayList()
        for (entity in entities) {
            result.add(persist(entity))
        }
        return result
    }

    @Transactional
    override fun <S : T> persistAndFlush(entity: S): S {
        persist(entity)
        entityManager.flush()
        return entity
    }

    @Transactional
    override fun <S : T> persist(entity: S): S {
        entityManager.persist(entity)
        return entity
    }

    protected fun session(): Session = entityManager.unwrap(Session::class.java)

    /**
     * Dog water section!
     */
    override fun findAll(): List<T> = throw UnsupportedOperationException(
        "Fetching all records from a given database table is a terrible idea!"
    )

    override fun <S : T> saveAllAndFlush(entities: Iterable<S>): List<S> = unsupportedSave()
    override fun <S : T> saveAndFlush(entity: S): S = unsupportedSave()
    override fun <S : T> saveAll(entities: Iterable<S>): List<S> = unsupportedSave()
    override fun <S : T> save(entity: S): S = unsupportedSave()

    protected fun <S : T?> unsupportedSave(): S {
        throw UnsupportedOperationException("There's no such thing as a save method in JPA, so don't use this hack!")
    }
}
