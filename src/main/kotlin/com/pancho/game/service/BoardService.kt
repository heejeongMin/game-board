package com.pancho.game.service

import com.pancho.game.exception.BusinessServiceException
import com.pancho.game.exception.DuplicatedDataException
import com.pancho.game.exception.ErrorCode
import com.pancho.game.exception.ErrorDto
import com.pancho.game.domain.GameBoard
import com.pancho.game.domain.GameBoardRepository
import com.pancho.game.dto.*
import io.jsonwebtoken.Jwts
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    val gameBoardRepository: GameBoardRepository,
    val userService: UserService
) {

    @Transactional
    fun create(gameBoardDto: GameBoardDto, jwt: String): GameBoardDto {
        val gameBoard = gameBoardRepository.findByGameNameAndGroupName(gameBoardDto.gameName, gameBoardDto.groupName)

        if (gameBoard != null) throw DuplicatedDataException( "The provided game name already existed in the group")

        val userDto = userService.getUser(jwt)
        val gameBoardCreated = gameBoardRepository.save(GameBoard.create(gameBoardDto, userDto.username))

        return GameBoardDto.from(gameBoardCreated)
    }

    @Transactional(readOnly = true)
    fun retrieveAll(jwt: String): List<GameBoardDto> {
        val userDto = userService.getUser(jwt)
        val gameBoard = gameBoardRepository.findByIsDeletedIsAndCreatedByOrderByCreatedOnDesc(false, userDto.username)
        return gameBoard?.map { GameBoardDto.from(it) } ?: emptyList()
    }

    @Transactional
    fun delete(id: Long) {
        val gameBoard =
            gameBoardRepository.findByIdIsAndIsDeletedIs(id, false) ?: throw Exception("Cannot find the game board")
        gameBoard.isDeleted = true
    }

    @Transactional
    fun edit(gameBoardId: Long, playersToUpdate: List<PlayerDto>) {
        val gameBoard = gameBoardRepository.findByIdOrNull(gameBoardId) ?: throw Exception("game board not found")
        gameBoard.updatePlayers(playersToUpdate)
        gameBoardRepository.save(gameBoard)
    }

    @Transactional
    fun deletePlayer(gameBoardId: Long, playerId: Long) {
        val gameBoard = gameBoardRepository.findByIdOrNull(gameBoardId) ?: throw Exception("game board not found")
        gameBoard.deletePlayer(playerId)
        gameBoardRepository.save(gameBoard)
    }
}