package kz.abyl.arbuz.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kz.abyl.arbuz.presentation.util.ProgressScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    padding: PaddingValues,
    onBadgeCountChange: (Int) -> Unit
) {

    var page by remember {
        mutableIntStateOf(1)
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeScreenEvent.GetPhotoCountFromDatabase)
        viewModel.onEvent(HomeScreenEvent.GetPhotos(page))
    }

    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.badgeCount) {
        onBadgeCountChange(state.badgeCount)
    }

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
                items = state.photos,
                key = {
                    it.id
                }
            ) { photo ->
                ProductItem(
                    onPlusClick = {
                        viewModel.onEvent(HomeScreenEvent.AddOrIncreaseProductToCart(photo))
                        viewModel.onEvent(HomeScreenEvent.GetPhotoCountFromDatabase)
                    },
                    onMinusClick = {
                        viewModel.onEvent(HomeScreenEvent.RemoveOrDecreaseProductToCart(photo))
                        viewModel.onEvent(HomeScreenEvent.GetPhotoCountFromDatabase)
                    },
                    photo = photo
                )
            }
        }
    }
}