package kz.abyl.arbuz.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.abyl.arbuz.data.local.PhotoDatabase
import kz.abyl.arbuz.domain.model.Photo
import kz.abyl.arbuz.domain.repository.PhotoRepository
import kz.abyl.arbuz.util.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    init {
        getPhotos(1)
        getPhotoCountFromDatabase()
    }

    fun onEvent(event: HomeScreenEvent) {
        when(event) {
            is HomeScreenEvent.AddOrIncreaseProductToCart -> {
                insertOrUpdatePhotoToDatabase(event.photo)
                getPhotoCountFromDatabase()
            }
            is HomeScreenEvent.RemoveOrDecreaseProductToCart -> {
                removeOrUpdatePhotoToDatabase(event.photo)
                getPhotoCountFromDatabase()
            }
            is HomeScreenEvent.GetPhotoCountFromDatabase -> getPhotoCountFromDatabase()
        }
    }

    private fun getPhotoCountFromDatabase() {
        viewModelScope.launch {
            val count = photoRepository.getPhotoCountFromDatabase()
            _state.value = _state.value.copy(badgeCount = count)
        }
    }

    private fun removeOrUpdatePhotoToDatabase(photo: Photo) {
        viewModelScope.launch {
            photoRepository.decreaseOrDeletePhotoToDatabase(photo)
            getPhotoCountFromDatabase()
        }
    }

    private fun insertOrUpdatePhotoToDatabase(photo: Photo) {
        viewModelScope.launch {
            photoRepository.insertOrUpdatePhotoToDatabase(photo)
            getPhotoCountFromDatabase()
        }
    }

    private fun getPhotos(page: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            photoRepository.getListOfPhotos(page).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            photos = result.data ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}