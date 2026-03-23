package shop.lighthappy.app.di

import shop.lighthappy.app.data.repository.CartRepository
import shop.lighthappy.app.data.repository.IKEHPOnboardingRepo
import shop.lighthappy.app.data.repository.OrderRepository
import shop.lighthappy.app.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        IKEHPOnboardingRepo(
            ikehpOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}