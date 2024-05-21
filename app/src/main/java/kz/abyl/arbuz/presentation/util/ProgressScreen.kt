package kz.abyl.arbuz.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kz.abyl.arbuz.ui.theme.Green

@Composable
fun ProgressScreen(
    paddingValues: PaddingValues
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color.White)
    ) {
        CircularProgressIndicator(
            color = Green
        )
    }
}

@Preview
@Composable
fun ProgressScreenPreview() {
    ProgressScreen(PaddingValues())
}