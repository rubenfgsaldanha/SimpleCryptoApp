package com.example.simplecryptoapp.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simplecryptoapp.presentation.coin_detail.CoinDetailNavArgs
import com.example.simplecryptoapp.presentation.coin_list.components.CoinListItem
import com.example.simplecryptoapp.presentation.destinations.CoinDetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun CoinListScreen(
    navigator: DestinationsNavigator,
    viewModel: CoinListViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()){

        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.coins){ coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        navigator.navigate(CoinDetailScreenDestination(CoinDetailNavArgs(coin.id)))
                    }
                )
            }
        }

        if(state.errorString.isNotBlank()){
            Text(
                text = state.errorString,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if(state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}