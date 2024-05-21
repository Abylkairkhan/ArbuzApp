package kz.abyl.arbuz.presentation.favorite

import kz.abyl.arbuz.domain.model.Photo

sealed class FavoriteScreenEvent {

    data object GetListOfPhotos: FavoriteScreenEvent()

    data class IncreaseCountOfPhoto(val photo: Photo): FavoriteScreenEvent()

    data class DecreaseOrDeleteCountOfPhoto(val photo: Photo): FavoriteScreenEvent()


}