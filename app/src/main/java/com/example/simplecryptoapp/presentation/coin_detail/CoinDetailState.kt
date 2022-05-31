package com.example.simplecryptoapp.presentation.coin_detail

import com.example.simplecryptoapp.domain.model.Coin
import com.example.simplecryptoapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val errorString: String = ""
)
