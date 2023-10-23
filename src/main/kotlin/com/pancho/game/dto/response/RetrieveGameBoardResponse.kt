package com.pancho.game.dto.response

import com.pancho.game.domain.GameScoring
import com.pancho.game.dto.GameBoardDto
import com.pancho.game.dto.PlayerDto

data class RetrieveGameBoardResponse (
    val id: Long,
    val gameName: String,
    val groupName: String,
    val players: List<PlayerInfo>,
    val scoring: GameScoring
) {

    companion object {
        fun from(gameBoardDto : GameBoardDto) : RetrieveGameBoardResponse{
            return RetrieveGameBoardResponse(
                id = gameBoardDto.id!!,
                gameName = gameBoardDto.gameName,
                groupName = gameBoardDto.groupName,
                players = gameBoardDto.players.map { PlayerInfo.from(it) }.sortedBy { it.score },
                scoring = gameBoardDto.scoring
            )
        }
    }
}

class PlayerInfo (
    val id : Long,
    val name : String,
    val score : Int
) {
    companion object {
        fun from(player : PlayerDto) :PlayerInfo {
            return PlayerInfo(
                id = player.id!!,
                name = player.playerName,
                score = player.score
            )
        }
    }
}