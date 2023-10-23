package com.pancho.game.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime

@Entity
@Table(name = "user")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var username: String = ""

    @Column
    var password: String = ""
        @JsonIgnore
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    @CreatedDate
    @Column(name = "created_on")
    var createdOn: LocalDateTime = LocalDateTime.now()

    @Column(name = "created_by")
    var createdBy: String = ""

    @LastModifiedDate
    @Column(name = "updated_on")
    var updatedOn: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_by")
    var updatedBy: String = ""

    companion object {
        fun create(username: String, password: String): User {
            val user = User()
            val now = LocalDateTime.now()

            user.username = username
            user.password = password
            user.createdOn = now
            user.updatedOn = now
            user.createdBy = username
            user.updatedBy = username

            return user
        }
    }
}
