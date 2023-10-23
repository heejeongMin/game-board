package com.pancho.game.dto

import com.pancho.game.domain.GameBoard
import com.pancho.game.domain.GameScoring
import com.pancho.game.dto.request.CreateGameBoardRequest
import com.pancho.game.dto.request.EditBoardRequest
import java.time.LocalDateTime

data class GameBoardDto(
    val id : Long? = null,
    val gameName : String,
    val groupName : String,
    val players : List<PlayerDto>,
    val scoring : GameScoring,
    var createdOn : LocalDateTime? = null,
    val createdBy : String? = null,
    var updatedOn : LocalDateTime? = null,
    val updatedBy : String? = null
) {

    companion object {
        fun from(request : CreateGameBoardRequest) : GameBoardDto {
            return GameBoardDto(
                gameName =  request.gameName,
                groupName = request.groupName,
                players = request.players.map { PlayerDto.from(it) },
                scoring = request.scoring
            )
        }

        fun from(gameBoard : GameBoard) : GameBoardDto {
            return GameBoardDto(
                id = gameBoard.id!!,
                gameName =  gameBoard.gameName,
                groupName = gameBoard.groupName,
                players = gameBoard.players.map { PlayerDto.from(it) },
                scoring = gameBoard.gameScoring
            )
        }
    }
}