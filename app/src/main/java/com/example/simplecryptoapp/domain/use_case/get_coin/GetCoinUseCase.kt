package com.example.simplecryptoapp.domain.use_case.get_coin

import com.example.simplecryptoapp.common.Resource
import com.example.simplecryptoapp.data.remote.dto.toCoin
import com.example.simplecryptoapp.data.remote.dto.toCoinDetail
import com.example.simplecryptoapp.domain.model.Coin
import com.example.simplecryptoapp.domain.model.CoinDetail
import com.example.simplecryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {


    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {

        try {

            emit(Resource.Loading())
            val coinDetail = repository.getCoinById(coinId)
            emit(Resource.Success(coinDetail.toCoinDetail()))

        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Oops, something went wrong"))
        } catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }

    }

}