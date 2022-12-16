package com.project.meomi.domain.user.service

import com.project.meomi.domain.user.presentation.data.dto.ReissueTokenDto
import com.project.meomi.domain.user.presentation.data.dto.TokenDto

interface ReissueTokenService {

    fun reissueToken(dto: ReissueTokenDto): TokenDto

}