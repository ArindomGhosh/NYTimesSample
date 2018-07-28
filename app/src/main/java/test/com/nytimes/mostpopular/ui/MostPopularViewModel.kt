package test.com.nytimes.mostpopular.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import test.com.nytimes.common.Resource
import test.com.nytimes.mostpopular.model.MostPopularParameters
import test.com.nytimes.mostpopular.model.MostPopularRepository
import test.com.nytimes.mostpopular.model.MostPopularResponse

class MostPopularViewModel(mostPopularRepository: MostPopularRepository): ViewModel(){
    val parameters: MutableLiveData<MostPopularParameters> = MutableLiveData()
    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()
    val mostPopular: LiveData<Resource<MostPopularResponse>> = Transformations.switchMap(parameters,{
        mostPopularRepository.getMostPopularList(it.section,it.period,it.apiKey)
    })
}