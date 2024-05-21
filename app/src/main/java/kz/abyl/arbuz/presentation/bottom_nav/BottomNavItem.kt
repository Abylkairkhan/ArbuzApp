package kz.abyl.arbuz.presentation.bottom_nav

data class BottomNavItem(
    val name: String,
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val badges: Int
)
