package kz.abyl.arbuz.presentation.bottom_nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kz.abyl.arbuz.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    navController: NavController,
    badgeCount: Int
) {

    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Главная",
            route = "home",
            selectedIcon = R.drawable.home_icon_fill,
            unselectedIcon = R.drawable.home_icon,
            badges = 0
        ),
        BottomNavItem(
            name = "Корзина",
            route = "favorite",
            selectedIcon = R.drawable.favorite_icon_fill,
            unselectedIcon = R.drawable.favorite_icon,
            badges = badgeCount
        )
    )

    var selected by remember {
        mutableIntStateOf(0)
    }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        bottomNavItems.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = index == selected,
                onClick = {
                    selected = index
                    navController.navigate(bottomNavItem.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        BadgedBox(
                            badge = {
                                if (bottomNavItem.badges != 0) {
                                    Badge {
                                        Text(text = bottomNavItem.badges.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    if (index == selected) bottomNavItem.selectedIcon else bottomNavItem.unselectedIcon
                                ),
                                contentDescription = ""
                            )
                        }
                        Text(
                            text = bottomNavItem.name,
                            fontSize = 12.sp
                        )
                    }
                }
            )
        }
    }
}