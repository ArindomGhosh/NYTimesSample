package test.com.nytimes

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import test.com.nytimes.mostpopular.model.MostPopularRepository
import test.com.nytimes.mostpopular.ui.MostPopularViewModel

class ViewModelFactory(private val mostPopularRepository:MostPopularRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass){
        MostPopularViewModel::class.java -> MostPopularViewModel(mostPopularRepository) as T
        else -> throw IllegalArgumentException("Unknow model class.")
    }

}