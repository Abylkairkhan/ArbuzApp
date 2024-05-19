package kz.abyl.arbuz.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.abyl.arbuz.domain.repository.PhotoRepository
import kz.abyl.arbuz.util.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())

    init {
        getPhotos(1)
    }

    private fun getPhotos(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository
                .getListOfPhotos(page)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            withContext(Dispatchers.Main) {
                                state = state.copy(
                                    isLoading = false,
                                    photos = result.data ?: emptyList()
                                )
                                Log.d("MyLog", result.data?.get(1)?.altDescription ?: "null")
                            }
                        }

                        is Resource.Error -> {
                            withContext(Dispatchers.Main) {
                                state = state.copy(
                                    isLoading = false,
                                    error = result.message
                                )
                            }
                        }

                        is Resource.Loading -> {
                            withContext(Dispatchers.Main) {
                                state = state.copy(
                                    isLoading = true
                                )
                            }
                        }
                    }
                }
        }
    }

}