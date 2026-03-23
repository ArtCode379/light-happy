package shop.lighthappy.app.di

import shop.lighthappy.app.ui.viewmodel.AppViewModel
import shop.lighthappy.app.ui.viewmodel.CartViewModel
import shop.lighthappy.app.ui.viewmodel.CheckoutViewModel
import shop.lighthappy.app.ui.viewmodel.IKEHPOnboardingVM
import shop.lighthappy.app.ui.viewmodel.OrderViewModel
import shop.lighthappy.app.ui.viewmodel.ProductDetailsViewModel
import shop.lighthappy.app.ui.viewmodel.ProductViewModel
import shop.lighthappy.app.ui.viewmodel.IKEHPSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        IKEHPSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        IKEHPOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}