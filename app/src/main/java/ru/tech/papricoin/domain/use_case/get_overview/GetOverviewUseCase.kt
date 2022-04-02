package ru.tech.papricoin.domain.use_case.get_overview

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tech.papricoin.data.remote.dto.overview
import ru.tech.papricoin.domain.model.Overview
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import javax.inject.Inject

class GetOverviewUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(): Flow<Result<Overview>> = flow {
        try {
            val overviewDto = repository.getOverview()
            emit(Result.success(overviewDto.overview))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}