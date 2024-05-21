package kz.abyl.arbuz.presentation.favorite

import kz.abyl.arbuz.domain.model.Photo

data class FavoriteScreenState (
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)