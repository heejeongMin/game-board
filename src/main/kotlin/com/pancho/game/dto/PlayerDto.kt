package com.pancho.game.dto

import com.pancho.game.domain.Player
import com.pancho.game.dto.request.PlayerUpdateRequest
import java.time.LocalDateTime

data class PlayerDto (
    val id : Long? = null,
    val playerName : String,
    val score : Int = 0,
    val createdOn: LocalDateTime = LocalDateTime.now(),
    val createdBy: String? = null
) {
    companion object {
        fun from(playerName : String) : PlayerDto {
            return PlayerDto(
                playerName = playerName,
                createdBy = "pancho"
            )
        }

        fun from(playerUpdateRequest : PlayerUpdateRequest) : PlayerDto {
            return PlayerDto(
                id = playerUpdateRequest.id,
                playerName = playerUpdateRequest.name,
                score = playerUpdateRequest.score
            )
        }

        fun from(player: Player) : PlayerDto {
            return PlayerDto(
                id = player.id,
                playerName = player.playerName,
                score = player.score,
                createdOn = player.createdOn,
                createdBy = player.createdBy
            )
        }
    }
}
