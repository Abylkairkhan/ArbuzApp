package kz.abyl.arbuz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.AndroidEntryPoint
import kz.abyl.arbuz.presentation.bottom_nav.BottomNavigationBar
import kz.abyl.arbuz.presentation.home.HomeScreen
import kz.abyl.arbuz.ui.theme.ArbuzTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArbuzTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar()
                    }
                ) {
                    val padding = it
                    HomeScreen(padding = padding)
                }
            }
        }
    }
}