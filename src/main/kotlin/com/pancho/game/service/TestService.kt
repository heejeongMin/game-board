package com.pancho.game.service

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.ContextStoppedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class TestService(
    @Value("\${spring.datasource.url}")
    private val datasource : String
) {

    @EventListener
    fun onStartup(event : ApplicationReadyEvent) { LOGGER.info { "startup $datasource" } }

    @EventListener
    fun onShutdown(event : ContextStoppedEvent) { LOGGER.info { "shutdown" } }

    companion object {
        private val LOGGER = KotlinLogging.logger {  }
    }

}