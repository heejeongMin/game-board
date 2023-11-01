package com.pancho.game

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class BoardApplication

fun main(args: Array<String>) {
	println("test 1")
	runApplication<BoardApplication>(*args)
}
