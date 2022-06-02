package ikhwan.binar.myretrofitcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.myretrofitcompose.model.staff.GetStaffResponseItem
import ikhwan.binar.myretrofitcompose.utils.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StaffViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel(){

    private var getStaffResponse = MutableStateFlow(emptyList<GetStaffResponseItem>())
    val dataState: StateFlow<List<GetStaffResponseItem>>
    get() = getStaffResponse


    init {
        viewModelScope.launch {
            getStaffResponse.value = newsRepository.getAllStaff()
        }
    }
}