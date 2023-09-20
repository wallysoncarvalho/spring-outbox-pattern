package com.sadybox.springoutboxpattern

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class SpringOutboxPatternApplication

fun main(args: Array<String>) {
	runApplication<SpringOutboxPatternApplication>(*args)
}
