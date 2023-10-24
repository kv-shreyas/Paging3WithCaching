package com.shreyasKv.paging3withcachingandcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.shreyasKv.paging3withcachingandcompose.presentation.DrinkScreen
import com.shreyasKv.paging3withcachingandcompose.presentation.DrinkViewModel
import com.shreyasKv.paging3withcachingandcompose.ui.theme.Paging3WithCachingAndComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
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
                    Column {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Shreyas Cafe",
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center, // Set text alignment to center
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Black // Customize the title text color
                                )
                            },
                            modifier = Modifier.background(Color.Gray), // Set the background color here
                        )
                        DrinkScreen(beers = beers)
                    }
                }
            }
        }//
    }
}