package kz.abyl.arbuz.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kz.abyl.arbuz.domain.model.Photo
import kz.abyl.arbuz.presentation.util.ProgressScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    padding: PaddingValues
) {

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        ProgressScreen(padding)
    } else {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp),
            columns = GridCells.Fixed(3)
        ) {
            items(
                items =  state.photos,
                key = {
                    it.id
                }
            ) { photo ->
                ProductItem(
                    onPlusClick = {
                        viewModel.onEvent(HomeScreenEvent.AddProductToCart(photo))
                    },
                    onMinusClick = {

                    },
                    photo = photo
                )
            }
        }
    }
}