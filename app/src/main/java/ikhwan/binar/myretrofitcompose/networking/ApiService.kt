package ikhwan.binar.myretrofitcompose.networking

import ikhwan.binar.myretrofitcompose.model.GetNewsResponseItem
import ikhwan.binar.myretrofitcompose.model.staff.GetStaffResponseItem
import retrofit2.http.GET

interface ApiService {

    @GET("news")
    suspend fun getAllNews() : List<GetNewsResponseItem>

    @GET("staf")
    suspend fun getAllStaff() : List<GetStaffResponseItem>
}