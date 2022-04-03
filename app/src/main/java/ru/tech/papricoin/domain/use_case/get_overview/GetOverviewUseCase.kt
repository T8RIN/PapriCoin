package ru.tech.papricoin.domain.use_case.get_overview

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tech.papricoin.data.remote.dto.overview
import ru.tech.papricoin.domain.model.Overview
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import ru.tech.papricoin.presentation.utils.Action
import javax.inject.Inject

class GetOverviewUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(): Flow<Action<Overview>> = flow {
        try {
            emit(Action.Loading())
            val overviewDto = repository.getOverview()
            emit(Action.Success(overviewDto.overview))
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
        }
    }
}