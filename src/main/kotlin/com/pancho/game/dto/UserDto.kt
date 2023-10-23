package com.pancho.game.dto

import com.pancho.game.domain.User

data class UserDto(
    val id: Long,
    val username: String
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                id = user.id!!,
                username = user.username
            )
        }
    }
}