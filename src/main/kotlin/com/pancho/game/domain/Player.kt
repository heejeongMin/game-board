package com.pancho.game.domain

import com.pancho.game.dto.PlayerDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "player")
class Player (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "game_board_id")
    val gameBoard: GameBoard? = null,

    @Column(name = "player_name")
    val playerName : String,

    @Column(name = "player_score")
    var score : Int,

    @Column(name = "created_on")
    val createdOn: LocalDateTime,

    @Column(name = "created_by")
    val createdBy: String,
) {

    companion object {
        fun create(playerDto : PlayerDto, gameBoard: GameBoard) : Player {
            return Player(
                gameBoard = gameBoard,
                playerName = playerDto.playerName,
                score = 0,
                createdOn = LocalDateTime.now(),
                createdBy = "pancho"
            )
        }
    }

    fun updateScore(playerDto : PlayerDto) {
        this.score = playerDto.score
    }
}