package ui.service

import android.app.Application
import android.support.v7.app.AppCompatActivity
import dagger.Component
import ui.app.AppComponent
import ui.app.ServiceScope

/** Dagger component interface for an [AppCompatActivity] */
@Component(dependencies = [AppComponent::class], modules = [ExampleServiceModule::class])
@ServiceScope
interface ExampleServiceComponent {
    fun injectIn(exampleService: ExampleService)

    fun application(): Application

    /** Dagger Builder for [ExampleServiceComponent] */
    @Component.Builder interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun exampleServiceModule(module: ExampleServiceModule): Builder
        fun build(): ExampleServiceComponent
    }
}