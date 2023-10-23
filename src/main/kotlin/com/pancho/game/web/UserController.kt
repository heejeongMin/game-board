package com.pancho.game.web

import com.pancho.game.dto.UserDto
import com.pancho.game.dto.request.CreateUserRequest
import com.pancho.game.dto.request.UserLoginRequest
import com.pancho.game.exception.NotAuthenticatedException
import com.pancho.game.service.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/game")
class UserController(private val userService: UserService) {

    @PostMapping
    fun register(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<UserDto> =
        ResponseEntity.ok(userService.createUser(createUserRequest.username, createUserRequest.password))

    @PostMapping("/login")
    fun login(@RequestBody userLoginRequest: UserLoginRequest, response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = userService.loginUser(userLoginRequest.username, userLoginRequest.password)
        response.addCookie(cookie)
        return ResponseEntity.ok("success")
    }

    @GetMapping
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<UserDto> {
        if(jwt == null) {
            throw NotAuthenticatedException()
        }

        return ResponseEntity.ok(userService.getUser(jwt))
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = userService.logoutUser()
        response.addCookie(cookie)
        return ResponseEntity.ok("success")
    }

    @PostMapping("/test2")
    fun test2() : ResponseEntity<Any> {
        return ResponseEntity.status(500).body(test())
    }
}

class test (
    val errorCode: String = "invalid_format",
    val errorMessage: String = "Invalid request format."
)
