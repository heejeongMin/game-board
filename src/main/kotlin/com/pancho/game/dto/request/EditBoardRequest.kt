package com.pancho.game.dto.request


data class EditBoardRequest (
    val players : List<PlayerUpdateRequest>,
)

data class PlayerUpdateRequest (
    val id : Long?,
    val name : String,
    val score : Int
)