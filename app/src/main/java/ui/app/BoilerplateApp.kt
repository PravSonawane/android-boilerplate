package ui.app

import android.app.Application

class BoilerplateApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.injectIn(this)
    }
}