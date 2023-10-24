package com.shreyasKv.paging3withcachingandcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.shreyasKv.paging3withcachingandcompose.data.local.DrinkEntity
import com.shreyasKv.paging3withcachingandcompose.data.mappers.toDrink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(pager: Pager<Int, DrinkEntity>) : ViewModel() {

    val bearPagingFlow =
        pager.flow
            .map { pagingData -> pagingData.map { it.toDrink() } }
            .cachedIn(viewModelScope)
}