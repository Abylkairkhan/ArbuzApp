package kz.abyl.arbuz.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.abyl.arbuz.domain.model.Photo
import kz.abyl.arbuz.domain.repository.PhotoRepository
import kz.abyl.arbuz.util.Resource
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteScreenState())
    val state: StateFlow<FavoriteScreenState> = _state.asStateFlow()

    fun onEvent(event: FavoriteScreenEvent) {
        when (event) {
            is FavoriteScreenEvent.GetListOfPhotos -> getListOfPhotos()
            is FavoriteScreenEvent.IncreaseCountOfPhoto -> increaseCountOfPhoto(event.photo)
            is FavoriteScreenEvent.DecreaseOrDeleteCountOfPhoto -> decreaseOrDeleteCountOfPhoto(event.photo)
            is FavoriteScreenEvent.GetPhotoCountFromDatabase -> getPhotoCountFromDatabase()
        }
    }

    private fun getPhotoCountFromDatabase() {
        viewModelScope.launch {
            val count = photoRepository.getPhotoCountFromDatabase()
            _state.value = _state.value.copy(badgeCount = count)
        }
    }

    private fun decreaseOrDeleteCountOfPhoto(photo: Photo) {
        viewModelScope.launch {
            photoRepository.decreaseOrDeletePhotoToDatabase(photo)
            onEvent(FavoriteScreenEvent.GetListOfPhotos)
            onEvent(FavoriteScreenEvent.GetPhotoCountFromDatabase)
        }
    }

    private fun increaseCountOfPhoto(photo: Photo) {
        viewModelScope.launch {
            photoRepository.insertOrUpdatePhotoToDatabase(photo)
        }
    }

    private fun getListOfPhotos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            photoRepository.getAllPhotosFromDatabase().collect() { result ->
                when (result) {
                    is Resource.Success -> _state.value = _state.value.copy(
                        photos = result.data ?: emptyList()
                    )

                    is Resource.Loading -> _state.value =
                        _state.value.copy(isLoading = result.isLoading)

                    is Resource.Error -> _state.value = _state.value.copy(
                        error = result.message
                    )
                }

            }
        }
    }
}