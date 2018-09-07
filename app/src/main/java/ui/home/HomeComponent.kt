package ui.home

import dagger.Component
import data.foo.FooModule
import ui.app.FeatureScope
import ui.main.MainComponent
import ui.app.UiModule

@Component(
    dependencies = [MainComponent::class],
    modules = [
      UiModule::class,
      HomeVmModule::class,
      HomeModule::class,
      FooModule::class
    ]
)
@FeatureScope
interface HomeComponent {
  fun injectIn(fragment: HomeFragment)

  /** Dagger Builder for [HomeComponent] */
  @Component.Builder
  interface Builder {
    fun activityComponent(mainComponent: MainComponent): Builder
    fun homeModule(homeModule: HomeModule): Builder
    fun fooModule(fooModule: FooModule): Builder
    fun build(): HomeComponent
  }
}