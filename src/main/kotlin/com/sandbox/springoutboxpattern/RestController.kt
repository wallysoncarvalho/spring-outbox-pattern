package com.sandbox.springoutboxpattern

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class RestController(
    private val coolUseCase: CoolUseCase
) {

    @RequestMapping("/cool")
    fun cool() {
        coolUseCase.execute()
    }
}
