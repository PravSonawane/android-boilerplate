package ui.home

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.app.ViewModelKey

@Module
abstract class HomeVmModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeVm::class)
    abstract fun bindHomeVm(homeVm: HomeVm): ViewModel
}