package ikhwan.binar.myretrofitcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.myretrofitcompose.model.GetNewsResponseItem
import ikhwan.binar.myretrofitcompose.utils.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {
    private var getNewsResponse = MutableStateFlow(emptyList<GetNewsResponseItem>())
    val dataState : StateFlow<List<GetNewsResponseItem>>
    get() = getNewsResponse

    init {
        viewModelScope.launch {
            val news = newsRepository.getAllNews()
            getNewsResponse.value = news
        }
    }
}