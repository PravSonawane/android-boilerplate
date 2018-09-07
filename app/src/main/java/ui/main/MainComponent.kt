package ui.main

import android.app.Application
import dagger.Component
import ui.app.ActivityScope
import ui.app.AppComponent
import android.support.v7.app.AppCompatActivity

/** Dagger component interface for an [AppCompatActivity] */
@Component(dependencies = [AppComponent::class], modules = [MainModule::class])
@ActivityScope
interface MainComponent {
    fun injectIn(mainActivity: MainActivity)

    fun application(): Application

    /** Dagger Builder for [MainComponent] */
    @Component.Builder interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun mainModule(mainModule: MainModule): Builder
        fun build(): MainComponent
    }
}