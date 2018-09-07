package ui.service

import android.app.Application
import android.app.Service
import dagger.Module
import dagger.Provides
import ui.app.ServiceScope

@Module
class ExampleServiceModule(private val service: Service) {

    @Provides
    @ServiceScope
    fun application(): Application = service.application

}