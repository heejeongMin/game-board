package com.pancho.game.service

import com.pancho.game.domain.User
import com.pancho.game.domain.UserRepository
import com.pancho.game.dto.UserDto
import com.pancho.game.exception.DataNotFoundException
import com.pancho.game.exception.DuplicatedDataException
import com.pancho.game.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun createUser(username: String, password: String): UserDto {
        var user = userRepository.findByUsername(username)
        if (user != null) {
            throw DuplicatedDataException("user already exists")
        }

        return UserDto.from(userRepository.save(User.create(username, password)))
    }

    @Transactional(readOnly = true)
    fun loginUser(username: String, password: String): Cookie {
        val user = userRepository.findByUsername(username) ?: throw DataNotFoundException("user not found")

        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) //1day
            .signWith(SignatureAlgorithm.HS256, "secret")
            .compact()

        val cookie = Cookie("jwt", jwt) //more secure than just return the string jwt
        cookie.isHttpOnly = true
        cookie.maxAge = 100000

        return cookie
    }

    fun getUser(jwt: String): UserDto {
        return try {
            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
            val user = userRepository.getReferenceById(body.issuer.toLong())
            UserDto.from(user)
        } catch (ex: Exception) {
            throw InvalidTokenException()
        }

    }

    fun logoutUser() : Cookie {
        var cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        return cookie
    }
}