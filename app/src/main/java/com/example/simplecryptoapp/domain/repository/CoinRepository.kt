package com.example.simplecryptoapp.domain.repository

import com.example.simplecryptoapp.common.Resource
import com.example.simplecryptoapp.domain.model.Coin
import com.example.simplecryptoapp.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<Resource<List<Coin>>>

    fun getCoinById(coinId: String): Flow<Resource<CoinDetail>>

}