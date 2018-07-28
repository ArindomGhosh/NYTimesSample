package test.com.nytimes

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import test.com.nytimes.injection.applicationModule

class MainApplication: Application(), KodeinAware{
    override val kodein: Kodein = Kodein.lazy {
        import(applicationModule)
        import(androidModule(this@MainApplication))
    }
}