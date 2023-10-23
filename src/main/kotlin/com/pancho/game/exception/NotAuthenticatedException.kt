package com.pancho.game.exception

import org.springframework.http.HttpStatus

class NotAuthenticatedException : BusinessServiceException(
    ErrorDto(
        HttpStatus.UNAUTHORIZED,
        ErrorCauseDto(
            ErrorCode.NOT_AUTHENTICATED,
            "user not authenticated"
        )
    )
)