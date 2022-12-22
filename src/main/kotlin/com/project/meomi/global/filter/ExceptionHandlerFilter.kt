package com.project.meomi.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.meomi.domain.user.exception.UserNotFoundException
import com.project.meomi.global.error.response.ErrorResponse
import com.project.meomi.global.error.type.ErrorCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionHandlerFilter: OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { throwable ->
            when (throwable) {
                is ExpiredJwtException -> {
                    println("만료된 토큰")
                    println(request.getHeader("Authorization"))
                    setErrorResponse(ErrorCode.EXPIRED_TOKEN, response)
                }
                is JwtException -> {
                    println("유효하지 않은 토큰")
                    println(request.getHeader("Authorization"))
                    setErrorResponse(ErrorCode.INVALID_TOKEN, response)
                }
                is UserNotFoundException -> setErrorResponse(ErrorCode.USER_NOT_FOUND, response)
                else -> setErrorResponse(ErrorCode.INTERVAL_SERVER_ERROR, response)
            }
        }
    }

    fun setErrorResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        response.status = errorCode.status
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        println("response 됨 1")
        val errorResponse = ErrorResponse(errorCode.message, errorCode.status)
        println("response 됨 2")
        val errorResponseEntityToJson = ObjectMapper().writeValueAsString(errorResponse)
        println("response 됨 3")
        response.writer.write(errorResponseEntityToJson)
    }

}