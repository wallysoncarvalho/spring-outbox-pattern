package com.sadybox.springoutboxpattern

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ExecuteOutbox {

    @Scheduled(fixedRate = 1000)
    fun publishMesssages() {

        println("running schedule")

    }

}