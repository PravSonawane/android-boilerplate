package ui.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import ui.app.BoilerplateApp

class ExampleService : IntentService(ExampleService::class.simpleName) {

  private val exampleServiceComponent by lazy {
    DaggerExampleServiceComponent.builder()
        .appComponent((application as BoilerplateApp).appComponent)
        .exampleServiceModule(ExampleServiceModule(this))
        .build()
  }

  override fun onHandleIntent(intent: Intent?) {
    Log.d(TAG, "onHandleIntent:$intent")
  }

  override fun onCreate() {
    super.onCreate()
    Log.d(TAG, "onCreate")

    //dagger injection
    exampleServiceComponent.injectIn(this)

  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy")
  }

  companion object {
    val TAG = ExampleService::class.simpleName
  }
}