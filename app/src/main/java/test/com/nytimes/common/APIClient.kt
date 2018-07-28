package test.com.nytimes.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient{

    companion object {
        private val baseURL: String="http://api.nytimes.com/svc/"
        val client: Retrofit = Retrofit.Builder()
                        .baseUrl(baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
        }

}