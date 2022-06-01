package com.example.simplecryptoapp.domain.use_case.get_coin

import com.example.simplecryptoapp.common.Resource
import com.example.simplecryptoapp.domain.model.CoinDetail
import com.example.simplecryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {


    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = repository.getCoinById(coinId)

}