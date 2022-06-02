package com.example.simplecryptoapp.domain.use_case.get_coins

import com.example.simplecryptoapp.common.Resource
import com.example.simplecryptoapp.domain.model.Coin
import com.example.simplecryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = repository.getCoins()

}