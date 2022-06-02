package com.example.simplecryptoapp.domain.use_case.order_coins

import com.example.simplecryptoapp.domain.model.Coin
import com.example.simplecryptoapp.presentation.OrderType
import javax.inject.Inject

class OrderCoinsUseCase @Inject constructor(){

    operator fun invoke(orderType: OrderType, unorderedList: List<Coin>): List<Coin> =
        when(orderType){
            is OrderType.Rank -> unorderedList.sortedBy { it.rank }
            is OrderType.Name -> unorderedList.sortedBy { it.name }
            is OrderType.isActive -> unorderedList.sortedBy { it.isActive }
        }

}