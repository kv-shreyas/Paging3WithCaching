package com.shreyasKv.paging3withcachingandcompose.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items

import com.shreyasKv.paging3withcachingandcompose.domain.Drink

@Composable
fun DrinkScreen(
    beers:LazyPagingItems<Drink>
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = beers.loadState ){
        if(beers.loadState.refresh is LoadState.Error){
            Toast.makeText(context, "Error: "+ ((beers.loadState.refresh as LoadState.Error).error.message), Toast.LENGTH_SHORT).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize().padding(15.dp)) {
        if(beers.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                items(beers) { bee ->
                    if(bee != null) {
                        BeerItem(
                            beer = bee,
                            modifier = Modifier.fillMaxWidth()
                                .background(Color.LightGray)
                                .border(1.dp, Color.Black),
                        )
                    }
                }
                item {
                    if(beers.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}