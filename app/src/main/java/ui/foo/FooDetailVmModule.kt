package ui.foo

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.app.ViewModelKey

@Module
abstract class FooDetailVmModule {

    @Binds
    @IntoMap
    @ViewModelKey(FooDetailVm::class)
    abstract fun bindHomeVm(fooDetailVm: FooDetailVm): ViewModel
}