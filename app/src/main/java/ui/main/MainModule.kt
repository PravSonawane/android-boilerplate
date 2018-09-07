package ui.main

import android.app.Application
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import ui.app.ActivityScope

@Module
class MainModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun application(): Application = activity.application

}