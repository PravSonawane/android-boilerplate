package ui.app

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.app.Service

/** Used to annotate dagger components/sub components which that should live as long as a [Fragment] */
@Scope
@Retention(RUNTIME)
annotation class FeatureScope

/** Used to annotate dagger components/sub components which that should live as long as an [AppCompatActivity] */
@Scope
@Retention(RUNTIME)
annotation class ActivityScope

/** Used to annotate dagger components/sub components which that should live as long as a [Service] */
@Scope
@Retention(RUNTIME)
annotation class ServiceScope