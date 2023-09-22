package com.sandbox.springoutboxpattern

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import kotlin.random.Random

@Service
class CoolUseCase(
    private val coolEntityRepository: HibernateCustomRepository<CoolEntity>,
    private val outboxRepository: HibernateCustomRepository<Outbox>
) {

    @Transactional
    fun execute() {
        val entity = CoolEntity(name = "name ${Random.nextLong(1, 9999999999)}")

        coolEntityRepository.persist(entity)

        Outbox(
            type = "CoolEntityCreated",
            content = "essa é a mensagem ${Random.nextLong()}",
            scheduledTo = Instant.now().plusSeconds(10)
        ).let { outboxRepository.persist(it) }
    }
}
