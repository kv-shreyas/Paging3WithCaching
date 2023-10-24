package com.shreyasKv.paging3withcachingandcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.shreyasKv.paging3withcachingandcompose.presentation.DrinkScreen
import com.shreyasKv.paging3withcachingandcompose.presentation.DrinkViewModel
import com.shreyasKv.paging3withcachingandcompose.ui.theme.Paging3WithCachingAndComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Paging3WithCachingAndComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<DrinkViewModel>()
                    val beers = viewModel.bearPagingFlow.collectAsLazyPagingItems()
                    DrinkScreen(beers = beers)
                }
            }
        }//
    }
}