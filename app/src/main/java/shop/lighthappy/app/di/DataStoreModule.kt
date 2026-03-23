package shop.lighthappy.app.di

import shop.lighthappy.app.data.datastore.IKEHPOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { IKEHPOnboardingPrefs(androidContext()) }
}