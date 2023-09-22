package com.sandbox.springoutboxpattern

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.EntityManager
import org.hibernate.SessionFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ExecuteOutbox(
    private val outboxRepository: OutboxRepository,
    private val jmsTemplate: JmsTemplate
) {

    @Scheduled(fixedRate = 3000)
    fun publishMessages() =runCatching{
        val outboxMessages = outboxRepository.findAllToBeProcessed()

        logger.debug { "Found ${outboxMessages.size} messages to process." }

        outboxMessages.forEach { outbox ->
            logger.debug { "Processing message $outbox" }
            outbox.finish()
            jmsTemplate.send("fila_sop") { it.createTextMessage(outbox.content) }
        }

        outboxRepository.updateAll(outboxMessages)
    }
        .onSuccess { logger.debug { "Finished processing outbox messages successfully." } }
        .onFailure { logger.error(it.cause ?: it) { "Fudeu !!!" } }
        .getOrNull()

    private companion object{
        val logger = KotlinLogging.logger {  }
    }
}
