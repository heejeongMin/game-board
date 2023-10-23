package com.pancho.game.exception

import org.springframework.http.HttpStatus

class DataNotFoundException(message: String) : BusinessServiceException(
    ErrorDto(
        HttpStatus.BAD_REQUEST,
        ErrorCauseDto(
            ErrorCode.DUPLICATED_RECORD,
            message
        )
    )
)