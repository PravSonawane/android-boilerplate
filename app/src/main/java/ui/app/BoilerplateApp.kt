package ui.app

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class BoilerplateApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        setupLeakCanary()
        appComponent.injectIn(this)

    }

    private fun setupLeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this)) LeakCanary.install(this)
    }
}