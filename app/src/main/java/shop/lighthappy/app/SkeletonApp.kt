package shop.lighthappy.app

import android.app.Application
import shop.lighthappy.app.di.dataModule
import shop.lighthappy.app.di.dispatcherModule
import shop.lighthappy.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class IKEHPApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@IKEHPApp)
            modules(appModules)
        }
    }
}