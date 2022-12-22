package com.project.meomi.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.meomi.domain.user.exception.UserNotFoundException
import com.project.meomi.global.error.response.ErrorResponse
import com.project.meomi.global.error.type.ErrorCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
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
        println("필터 옴")
        runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { throwable ->
            when (throwable) {
                is ExpiredJwtException -> println("만료된 토큰") /*setErrorResponse(ErrorCode.EXPIRED_TOKEN, response)*/
                is JwtException -> setErrorResponse(ErrorCode.INVALID_TOKEN, response)
                is UserNotFoundException -> setErrorResponse(ErrorCode.USER_NOT_FOUND, response)
                else -> setErrorResponse(ErrorCode.INTERVAL_SERVER_ERROR, response)
            }
        }
    }

    fun setErrorResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        response.status = errorCode.status
        response.contentType = "application/json; charset=utf-8"
        val errorResponse = ErrorResponse(errorCode.message, errorCode.status)
        val errorResponseEntityToJson = ObjectMapper().writeValueAsString(errorResponse)
        response.writer.write(errorResponseEntityToJson)
    }

}