package com.pancho.game.domain

import com.pancho.game.dto.GameBoardDto
import com.pancho.game.dto.PlayerDto
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Table(name = "game_board")
@Entity
class GameBoard(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "game_name")
    val gameName: String,

    @Column(name = "game_scoring")
    val gameScoring: GameScoring = GameScoring.WINNING_COUNT,

    @Column(name = "group_name")
    val groupName: String,

    @OneToMany(mappedBy = "gameBoard", cascade = [CascadeType.ALL], orphanRemoval = true)
    var players: MutableList<Player> = mutableListOf(),

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false,

    @CreatedDate
    @Column(name = "created_on")
    var createdOn: LocalDateTime,

    @Column(name = "created_by")
    var createdBy: String,

    @LastModifiedDate
    @Column(name = "updated_on")
    var updatedOn: LocalDateTime,

    @Column(name = "updated_by")
    var updatedBy: String
) {

    companion object {
        fun create(gameBoardDto: GameBoardDto, username: String): GameBoard {
            val gameBoard = GameBoard(
                gameName = gameBoardDto.gameName,
                gameScoring = gameBoardDto.scoring,
                groupName = gameBoardDto.groupName,
                createdOn = LocalDateTime.now(),
                createdBy = username,
                updatedOn = LocalDateTime.now(),
                updatedBy = username
            )

            gameBoard.players = gameBoardDto.players.map { Player.create(it, gameBoard) }.toMutableList()

            return gameBoard;
        }
    }

    fun updatePlayers(playersToUpdate: List<PlayerDto>) {
        playersToUpdate.map {
            if (it.id == null) {
                this.players.add(Player.create(it, this))
            } else {
                this.players.find { player -> it.id == player.id }
                    .also { player -> player?.updateScore(it) }
            }
        }
    }

    fun deletePlayer(playerId: Long) {
        players.removeIf { it.id == playerId }
    }
}