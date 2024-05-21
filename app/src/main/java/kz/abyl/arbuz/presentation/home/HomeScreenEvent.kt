package kz.abyl.arbuz.presentation.home

import kz.abyl.arbuz.domain.model.Photo

sealed class HomeScreenEvent {

    data class AddOrIncreaseProductToCart(val photo: Photo): HomeScreenEvent()

    data class RemoveOrDecreaseProductToCart(val photo: Photo): HomeScreenEvent()

    data object GetPhotoCountFromDatabase: HomeScreenEvent()

}