package com.pancho.game.exception

open class BusinessServiceException(val error: ErrorDto) : Exception()