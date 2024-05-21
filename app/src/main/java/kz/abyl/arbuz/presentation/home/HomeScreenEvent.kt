package kz.abyl.arbuz.presentation.home

import kz.abyl.arbuz.domain.model.Photo

sealed class HomeScreenEvent {

    data class AddProductToCart(val photo: Photo): HomeScreenEvent()

}