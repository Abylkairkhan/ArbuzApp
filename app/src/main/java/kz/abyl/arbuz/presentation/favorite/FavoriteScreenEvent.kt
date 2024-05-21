package kz.abyl.arbuz.presentation.favorite

import kz.abyl.arbuz.domain.model.Photo
import kz.abyl.arbuz.presentation.home.HomeScreenEvent

sealed class FavoriteScreenEvent {

    data object GetListOfPhotos: FavoriteScreenEvent()

    data class IncreaseCountOfPhoto(val photo: Photo): FavoriteScreenEvent()

    data class DecreaseOrDeleteCountOfPhoto(val photo: Photo): FavoriteScreenEvent()

    data object GetPhotoCountFromDatabase: FavoriteScreenEvent()

}