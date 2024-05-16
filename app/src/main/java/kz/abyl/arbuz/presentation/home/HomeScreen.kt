package kz.abyl.arbuz.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    padding: PaddingValues
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Green),
        columns = GridCells.Adaptive(128.dp)
    ) {
        items(10) {
            ProductItem()
        }
    }
}