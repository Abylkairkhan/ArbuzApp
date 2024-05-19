package kz.abyl.arbuz.presentation.home

import kz.abyl.arbuz.domain.model.Photo

data class HomeScreenState (
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)