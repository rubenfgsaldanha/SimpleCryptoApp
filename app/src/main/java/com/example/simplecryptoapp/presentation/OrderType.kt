package com.example.simplecryptoapp.presentation

sealed class OrderType{

    object Rank: OrderType()

    object Name: OrderType()

    object isActive: OrderType()

}
