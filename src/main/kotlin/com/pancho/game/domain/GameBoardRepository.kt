package com.pancho.game.domain

import org.springframework.data.jpa.repository.JpaRepository

interface GameBoardRepository : JpaRepository<GameBoard, Long> {

    fun findByGameNameAndGroupName(gameName: String, groupName: String) : GameBoard?

    fun findByIsDeletedIsAndCreatedByOrderByCreatedOnDesc(isDeleted: Boolean, createdBy: String) : List<GameBoard>?

    fun findByIdIsAndIsDeletedIs(id: Long, isDeleted: Boolean) : GameBoard?
}