package ikhwan.binar.myretrofitcompose.utils

import ikhwan.binar.myretrofitcompose.model.GetNewsResponseItem
import ikhwan.binar.myretrofitcompose.model.staff.GetStaffResponseItem
import ikhwan.binar.myretrofitcompose.networking.ApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllNews() : List<GetNewsResponseItem> = apiService.getAllNews()
    suspend fun getAllStaff() : List<GetStaffResponseItem> = apiService.getAllStaff()
}