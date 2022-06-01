package com.example.simplecryptoapp.data.repository

import com.example.simplecryptoapp.common.Resource
import com.example.simplecryptoapp.data.remote.CoinPaprikaApi
import com.example.simplecryptoapp.data.remote.dto.toCoin
import com.example.simplecryptoapp.data.remote.dto.toCoinDetail
import com.example.simplecryptoapp.domain.model.Coin
import com.example.simplecryptoapp.domain.model.CoinDetail
import com.example.simplecryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository {

    override fun getCoins(): Flow<Resource<List<Coin>>> = flow{

        emit(Resource.Loading())
        val coins = api.getCoins()
        emit(Resource.Success(coins.map { it.toCoin() }))

    }.catch { exception ->
        when (exception) {
            is HttpException -> emit(Resource.Error(exception.localizedMessage ?: "Oops, something went wrong"))
            is IOException -> emit(Resource.Error("Couldn't reach server. Check your internet connection"))
            else -> emit(Resource.Error("An error occurred trying to get the coins"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> = flow {

        emit(Resource.Loading())
        val coinDetail = api.getCoinById(coinId)
        emit(Resource.Success(coinDetail.toCoinDetail()))

    }.catch { exception ->
        when(exception){
            is HttpException -> emit(Resource.Error(exception.localizedMessage ?: "Oops, something went wrong"))
            is IOException -> emit(Resource.Error("Couldn't reach server. Check your internet connection"))
            else -> emit(Resource.Error("An error occurred trying to get the coin by id"))
        }
    }.flowOn(Dispatchers.IO)

}