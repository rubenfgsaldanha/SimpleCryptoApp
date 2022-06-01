package com.example.simplecryptoapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplecryptoapp.common.Resource
import com.example.simplecryptoapp.domain.use_case.get_coin.GetCoinUseCase
import com.example.simplecryptoapp.presentation.destinations.CoinDetailScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        val args = CoinDetailScreenDestination.argsFrom(savedStateHandle)
        getCoinById(args.coinId)
    }

    private fun getCoinById(coinId: String){
        getCoinUseCase(coinId).onEach { result ->
            _state.value = when(result){
                is Resource.Success -> {
                    CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    CoinDetailState(errorString = result.message ?: "Oops, something bad happened")
                }
                is Resource.Loading -> {
                    CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}