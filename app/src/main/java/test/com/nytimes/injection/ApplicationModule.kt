package test.com.nytimes.injection

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import test.com.nytimes.ViewModelFactory
import test.com.nytimes.common.APIClient
import test.com.nytimes.mostpopular.model.MostPopularRepository
import test.com.nytimes.mostpopular.service.NYTimesService

val applicationModule = Kodein.Module{
    bind< NYTimesService>() with singleton {
        APIClient.client.create(NYTimesService::class.java)
    }
    bind<MostPopularRepository>() with singleton { MostPopularRepository(instance())}
    bind<ViewModelFactory>() with singleton { ViewModelFactory(instance()) }
}