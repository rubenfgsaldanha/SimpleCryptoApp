package com.example.simplecryptoapp.domain.use_case.get_coins

import com.example.simplecryptoapp.common.Resource
import com.example.simplecryptoapp.data.remote.dto.toCoin
import com.example.simplecryptoapp.domain.model.Coin
import com.example.simplecryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {


    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {

        try {

            emit(Resource.Loading())
            val coins = repository.getCoins()
            emit(Resource.Success(coins.map { it.toCoin() }))

        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Oops, something went wrong"))
        } catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }

    }

}