package com.pancho.game.config

import com.pancho.game.exception.BusinessServiceException
import com.pancho.game.exception.ErrorCauseDto
import com.pancho.game.exception.ErrorCode
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingRequestCookieException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(*[BusinessServiceException::class])
    fun handleBusinessServiceException(
        exception: BusinessServiceException,
        request: WebRequest
    ): ResponseEntity<ErrorCauseDto> {
        LOGGER.info { exception.error }
        return ResponseEntity(exception.error.cause, exception.error.httpCode)
    }

    @ExceptionHandler(*[MissingRequestCookieException::class])
    fun handleMissingRequestCookieException(
        exception: MissingRequestCookieException,
        request: WebRequest
    ): ResponseEntity<ErrorCauseDto> {
        return ResponseEntity(ErrorCauseDto.notAuthenticated(), HttpStatus.UNAUTHORIZED)
    }

    companion object {
        private val LOGGER = KotlinLogging.logger { }
    }
}