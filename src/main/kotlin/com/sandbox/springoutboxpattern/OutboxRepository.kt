package com.sandbox.springoutboxpattern

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class OutboxRepository(
    val entityManager: EntityManager
) : HibernateCustomRepository<Outbox>(entityManager) {

    fun findAllToBeProcessed(): List<Outbox> {
        val query = "select * from outbox where scheduled_to <= now() and processed_at is null for update skip locked limit 5"

        """
            delete from public.outbox where id = (
                select ob.id from public.outbox ob where ob.scheduled_to <= now() 
                    and ob.processed_at is null 
                    order by created_at asc 
                    for update skip locked limit 5
            ) returning *;
        """.trimIndent()

        return entityManager.createNativeQuery(query, Outbox::class.java).resultList as List<Outbox>
    }
}
