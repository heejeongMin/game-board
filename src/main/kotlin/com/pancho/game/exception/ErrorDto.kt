package com.pancho.game.exception

import org.springframework.http.HttpStatus

class ErrorDto (
    val httpCode : HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val cause : ErrorCauseDto
)

class ErrorCauseDto (
    val code : ErrorCode,
    val message : String,
) {
    companion object {
        fun notAuthenticated() : ErrorCauseDto{
            return ErrorCauseDto(ErrorCode.NOT_AUTHENTICATED, ErrorCode.NOT_AUTHENTICATED.name)
        }
    }
}

enum class ErrorCode {
    NO_DATA_FOUND,
    DUPLICATED_RECORD,
    INVALID_TOKEN,
    NOT_AUTHENTICATED,
}