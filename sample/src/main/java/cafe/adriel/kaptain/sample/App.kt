package cafe.adriel.kaptain.sample

import android.app.Application
import cafe.adriel.kaptain.Kaptain
import cafe.adriel.kaptain.add
import cafe.adriel.kaptain.sample.feature.a.FeatureAActivity
import cafe.adriel.kaptain.sample.feature.b.FeatureBActivity
import cafe.adriel.kaptain.sample.feature.shared.Destination
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    private val appModule = module {
        single {
            Kaptain {
                add<Destination.FeatureA, FeatureAActivity>()
                add<Destination.FeatureB, FeatureBActivity>()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}
