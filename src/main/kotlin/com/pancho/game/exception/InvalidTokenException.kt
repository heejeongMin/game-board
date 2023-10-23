package com.pancho.game.exception

import org.springframework.http.HttpStatus

class InvalidTokenException : BusinessServiceException(
    ErrorDto(
        HttpStatus.UNAUTHORIZED,
        ErrorCauseDto(
            ErrorCode.INVALID_TOKEN,
            "token is invalid"
        )
    )
)