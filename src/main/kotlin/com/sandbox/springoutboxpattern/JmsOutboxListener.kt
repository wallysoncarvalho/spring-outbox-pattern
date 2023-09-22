package com.sandbox.springoutboxpattern

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
class JmsOutboxListener {

    @JmsListener(destination = "fila_sop")
    fun listen(content: String) {
        logger.info { "Consumed message: $content" }
    }

    private companion object{
        val logger = KotlinLogging.logger {  }
    }
}
