package kz.abyl.arbuz.presentation.home

sealed class HomeScreenEvent {

    data class AddProductToCart(val id: Int): HomeScreenEvent()

}