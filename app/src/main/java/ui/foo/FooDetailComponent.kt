package ui.foo

import dagger.Component
import data.foo.FooModule
import ui.app.FeatureScope
import ui.app.UiModule
import ui.main.MainComponent

@Component(
    dependencies = [MainComponent::class],
    modules = [
      UiModule::class,
      FooDetailVmModule::class,
      FooDetailModule::class,
      FooModule::class
    ]
)
@FeatureScope
interface FooDetailComponent {
  fun injectIn(fragment: FooDetailFragment)

  /** Dagger Builder for [FooDetailComponent] */
  @Component.Builder
  interface Builder {
    fun activityComponent(mainComponent: MainComponent): Builder
    fun fooDetailModule(fooDetailModule: FooDetailModule): Builder
    fun fooModule(fooModule: FooModule): Builder
    fun build(): FooDetailComponent
  }
}