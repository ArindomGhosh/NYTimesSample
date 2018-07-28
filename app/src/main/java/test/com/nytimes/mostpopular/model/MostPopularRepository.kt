package test.com.nytimes.mostpopular.model

import android.arch.lifecycle.LiveData
import test.com.nytimes.common.Resource
import test.com.nytimes.mostpopular.service.NYTimesService

class MostPopularRepository(private val service: NYTimesService){

    fun getMostPopularList(section: String, period: Int,apiKey: String): LiveData<Resource<MostPopularResponse>> {
        val popularList = service.getMostPopular(section,period,apiKey)
        return PopularListLiveData<MostPopularResponse>(popularList)
    }
}