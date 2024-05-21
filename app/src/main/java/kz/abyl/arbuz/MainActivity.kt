package kz.abyl.arbuz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kz.abyl.arbuz.presentation.bottom_nav.BottomNavigationBar
import kz.abyl.arbuz.presentation.favorite.FavoriteScreen
import kz.abyl.arbuz.presentation.home.HomeScreen
import kz.abyl.arbuz.ui.theme.ArbuzTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArbuzTheme {
                val navController = rememberNavController()
                var badgeCount by remember { mutableIntStateOf(0) }
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            navController,
                            badgeCount
                        )
                    }
                ) { padding ->
                    NavHost(navController = navController, startDestination = "home" ) {
                        composable("home") {
                            HomeScreen(
                                padding = padding,
                                onBadgeCountChange = { newCount ->
                                    badgeCount = newCount
                                }
                            )
                        }
                        composable("favorite") {
                            FavoriteScreen(
                                padding = padding,
                                onBadgeCountChange = { newCount ->
                                    badgeCount = newCount
                                }
                            )

                        }
                    }
                }
            }
        }
    }
}