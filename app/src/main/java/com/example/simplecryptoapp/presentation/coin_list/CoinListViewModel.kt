package com.example.simplecryptoapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplecryptoapp.common.Resource
import com.example.simplecryptoapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    private var _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            _state.value = when(result){
                is Resource.Success -> {
                    CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    CoinListState(errorString = result.message ?: "Oops, something bad happened")
                }
                is Resource.Loading -> {
                    CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}