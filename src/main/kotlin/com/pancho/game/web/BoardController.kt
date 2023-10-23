package com.pancho.game.web

import com.pancho.game.dto.GameBoardDto
import com.pancho.game.dto.PlayerDto
import com.pancho.game.dto.request.CreateGameBoardRequest
import com.pancho.game.dto.request.EditBoardRequest
import com.pancho.game.dto.response.CreateGameBoardResponse
import com.pancho.game.dto.response.RetrieveGameBoardResponse
import com.pancho.game.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/game/board")
class BoardController(
    val boardService: BoardService,
) {

    @PostMapping
    fun createBoard(
        @CookieValue("jwt") jwt: String,
        @RequestBody request: CreateGameBoardRequest) =
        CreateGameBoardResponse.from(boardService.create(GameBoardDto.from(request), jwt))

    @GetMapping("/all")
    fun getBoard(@CookieValue("jwt") jwt: String): List<RetrieveGameBoardResponse> {
        return boardService.retrieveAll(jwt).map { RetrieveGameBoardResponse.from(it) }
    }

    @DeleteMapping("/{id}")
    fun deleteBoard(@PathVariable id: Long) {
        boardService.delete(id)
    }

    @PutMapping("/{id}")
    fun editBoard(
        @PathVariable id: Long,
        @RequestBody request: EditBoardRequest) =
        boardService.edit(id, request.players.map { PlayerDto.from(it) })

    @DeleteMapping("/{id}/player/{playerId}")
    fun deletePlayer(
        @PathVariable id: Long,
        @PathVariable playerId: Long) =
        boardService.deletePlayer(id, playerId)
}