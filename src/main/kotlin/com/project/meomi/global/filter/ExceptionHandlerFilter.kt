package com.project.meomi.global.filter

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
        }.onFailure { exception ->
            when (exception) {
                is ExpiredJwtException -> setErrorResponse(ErrorCode.EXPIRED_TOKEN, response)
                is JwtException -> setErrorResponse(ErrorCode.INVALID_TOKEN, response)
                is UserNotFoundException -> setErrorResponse(ErrorCode.USER_NOT_FOUND, response)
//                else -> setErrorResponse(ErrorCode.INTERVAL_SERVER_ERROR, response)
            }
        }
    }

    private fun setErrorResponse(errorCode: ErrorCode, response: HttpServletResponse): ResponseEntity<ErrorResponse> {
        response.status = errorCode.status
        response.contentType = "application/json; charset=utf-8"
        val errorResponse = ErrorResponse(errorCode.message, errorCode.status)
        return ResponseEntity(errorResponse, HttpStatus.valueOf(errorResponse.status))
    }

}