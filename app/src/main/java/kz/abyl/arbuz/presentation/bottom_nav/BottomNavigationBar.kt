package kz.abyl.arbuz.presentation.bottom_nav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kz.abyl.arbuz.R

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Home",
            route = "home",
            selectedIcon = R.drawable.home_icon_fill,
            unselectedIcon = R.drawable.home_icon
        ),
        BottomNavItem(
            name = "Favorite",
            route = "favorite",
            selectedIcon = R.drawable.favorite_icon_fill,
            unselectedIcon = R.drawable.favorite_icon
        )
    )

    var selected by remember {
        mutableIntStateOf(0)
    }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp)
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
                    Icon(
                        painter = painterResource(
                            if (index == selected) bottomNavItem.selectedIcon else bottomNavItem.unselectedIcon
                        ),
                        contentDescription = ""
                    )
                },
                label = {
                    Text(text = bottomNavItem.name)
                }
            )
        }
    }
}