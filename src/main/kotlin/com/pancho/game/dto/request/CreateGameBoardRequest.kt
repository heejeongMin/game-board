package com.pancho.game.dto.request

import com.pancho.game.domain.GameScoring

data class CreateGameBoardRequest (
    val gameName : String,
    val groupName : String,
    val players : List<String>,
    val scoring : GameScoring
)