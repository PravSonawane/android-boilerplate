package ui.app

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent {
    fun injectIn(boilerplateApp: BoilerplateApp)

    /** Dagger Builder for [AppComponent] */
    @Component.Builder interface Builder {
        @BindsInstance fun application(boilerplateApp: BoilerplateApp): Builder
        fun build(): AppComponent
    }
}