package com.pancho.game.dto.response

import com.pancho.game.domain.GameScoring
import com.pancho.game.dto.GameBoardDto

data class CreateGameBoardResponse(
    val gameName: String,
    val groupName: String,
    val players: List<String>,
    val scoring: GameScoring
) {

    companion object {
        fun from(gameBoardDto: GameBoardDto): CreateGameBoardResponse {
            return CreateGameBoardResponse(
                gameName = gameBoardDto.gameName,
                groupName = gameBoardDto.groupName,
                players = gameBoardDto.players.map { it.playerName },
                scoring = gameBoardDto.scoring
            )
        }
    }
}