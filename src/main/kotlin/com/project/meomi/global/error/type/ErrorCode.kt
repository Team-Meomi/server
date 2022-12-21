package com.project.meomi.global.error.type

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // USER
    DUPLICATE_EMAIL("중복된 이메일 입니다.", 409),
    DUPLICATE_STUNUM("중복된 학번 입니다.", 409),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", 404),
    PASSWORD_NOT_CORRECT("비밀번호가 일치하지 않습니다.", 400),


    // TOKEN
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 403),
    EXPIRED_TOKEN("만료된 토큰 입니다.", 403),
    REFRESH_TOKEN_EXPIRED("만료된 RefreshToken 입니다.", 403),


    // STUDY
    STUDY_NOT_FOUNT("스터디를 찾을 수 없습니다.", 404),
    STUDY_COUNT_OVER("스터디 최대 수용 인원을 초과했습니다.", 400),
    DUPLICATE_APPLICANT("중복된 신청자 입니다.", 409),
    APPLICANT_NOT_FOUNT("신청자를 찾을 수 없습니다.", 404),


    // SERVER
    INTERVAL_SERVER_ERROR("서버 오류 입니다.", 500)
    ;

}