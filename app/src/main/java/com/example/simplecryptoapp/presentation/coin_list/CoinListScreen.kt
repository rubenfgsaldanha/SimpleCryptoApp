package com.example.simplecryptoapp.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simplecryptoapp.presentation.OrderType
import com.example.simplecryptoapp.presentation.coin_detail.CoinDetailNavArgs
import com.example.simplecryptoapp.presentation.coin_list.components.CoinListItem
import com.example.simplecryptoapp.presentation.coin_list.components.OrderCoinsItem
import com.example.simplecryptoapp.presentation.destinations.CoinDetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun CoinListScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: CoinListViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()){

        LazyColumn(modifier = Modifier.fillMaxSize()){

            if(state.coins.isNotEmpty()){
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OrderCoinsItem(
                            modifier = Modifier.padding(start = 18.dp, top = 8.dp, bottom = 8.dp),
                            text = "#",
                            onClick = { viewModel.orderCoins(OrderType.Rank) }
                        )
                        OrderCoinsItem(
                            modifier = Modifier.padding(vertical = 8.dp),
                            text = "Name",
                            onClick = { viewModel.orderCoins(OrderType.Name) }
                        )
                        OrderCoinsItem(
                            modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp),
                            text = "isActive",
                            onClick = { viewModel.orderCoins(OrderType.isActive) }
                        )
                    }
                }
            }

            items(state.coins){ coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        navigator?.navigate(CoinDetailScreenDestination(CoinDetailNavArgs(coin.id)))
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
