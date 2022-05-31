package com.example.simplecryptoapp.domain.repository

import com.example.simplecryptoapp.data.remote.dto.CoinDetailDto
import com.example.simplecryptoapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

}