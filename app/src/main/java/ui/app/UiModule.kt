package ui.app

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ui.app.AppViewModelFactory

@Module
abstract class UiModule {
    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}