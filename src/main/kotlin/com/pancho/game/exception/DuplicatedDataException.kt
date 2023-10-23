package com.pancho.game.exception

import org.springframework.http.HttpStatus

class DuplicatedDataException(message: String) : BusinessServiceException(
    ErrorDto(
        HttpStatus.OK,
        ErrorCauseDto(
            ErrorCode.NO_DATA_FOUND,
            message
        )
    )
)