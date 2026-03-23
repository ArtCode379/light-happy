package shop.lighthappy.app.data.repository

import shop.lighthappy.app.data.datastore.IKEHPOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class IKEHPOnboardingRepo(
    private val ikehpOnboardingStoreManager: IKEHPOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return ikehpOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            ikehpOnboardingStoreManager.setOnboardedState(state)
        }
    }
}