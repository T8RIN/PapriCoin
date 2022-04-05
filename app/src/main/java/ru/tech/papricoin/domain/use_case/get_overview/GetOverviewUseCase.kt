package ru.tech.papricoin.domain.use_case.get_overview

import kotlinx.coroutines.flow.Flow
import ru.tech.papricoin.domain.model.Overview
import ru.tech.papricoin.domain.repository.PapriCoinRepository
import ru.tech.papricoin.presentation.ui.utils.Action
import javax.inject.Inject

class GetOverviewUseCase @Inject constructor(
    private val repository: PapriCoinRepository
) {
    operator fun invoke(): Flow<Action<Overview>> {
        return repository.getOverview()
    }
}