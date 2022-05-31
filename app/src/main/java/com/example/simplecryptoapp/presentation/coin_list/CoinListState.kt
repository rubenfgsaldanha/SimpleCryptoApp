package com.example.simplecryptoapp.presentation.coin_list

import com.example.simplecryptoapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val errorString: String = ""
)
