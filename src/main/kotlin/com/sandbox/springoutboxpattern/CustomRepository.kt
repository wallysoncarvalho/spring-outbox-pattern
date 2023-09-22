package com.sandbox.springoutboxpattern

/**
 * https://vladmihalcea.com/best-spring-data-jparepository/
 */
interface CustomRepository<T> {

    //The findAll method will trigger an UnsupportedOperationException
    @Deprecated("num usa essa merda, pela caridade.")
    fun findAll(): List<T>

    //Save methods will trigger an UnsupportedOperationException
    @Deprecated("num usa essa merda, pela caridade.")
    fun <S : T> save(entity: S): S

    @Deprecated("num usa essa merda, pela caridade.")
    fun <S : T> saveAll(entities: Iterable<S>): List<S>

    @Deprecated("num usa essa merda, pela caridade.")
    fun <S : T> saveAndFlush(entity: S): S

    @Deprecated("num usa essa merda, pela caridade.")
    fun <S : T> saveAllAndFlush(entities: Iterable<S>): List<S>

    //Persist methods are meant to save newly created entities
    fun <S : T> persist(entity: S): S

    fun <S : T> persistAndFlush(entity: S): S

    fun <S : T> persistAll(entities: Iterable<S>): List<S>

    fun <S : T> persistAllAndFlush(entities: Iterable<S>): List<S>

    //Merge methods are meant to propagate detached entity state changes if they are really needed
    fun <S : T> merge(entity: S): S

    fun <S : T> mergeAndFlush(entity: S): S

    fun <S : T> mergeAll(entities: Iterable<S>): List<S>

    fun <S : T> mergeAllAndFlush(entities: Iterable<S>): List<S>

    //Update methods are meant to force the detached entity state changes
    fun <S : T> update(entity: S): S

    fun <S : T> updateAndFlush(entity: S): S

    fun <S : T> updateAll(entities: Iterable<S>): List<S>

    fun <S : T> updateAllAndFlush(entities: Iterable<S>): List<S>
}
