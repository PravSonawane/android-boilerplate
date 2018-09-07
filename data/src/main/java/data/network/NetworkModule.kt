package data.network

import com.merryapps.data.BuildConfig
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 10L
private const val READ_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 10L

private const val BASE_URL = "http://10.0.2.2:8080/"

/**
 * //TODO add description here
 */
@Module
class NetworkModule {

  @Provides
  fun provideHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)


    //clientBuilder.addInterceptor(authHeaderInterceptor)

    return clientBuilder.build()
  }

  @Provides
  fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        //.addConverterFactory(EnvelopeConverterFactory())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  @Provides
  fun providesMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(LocalDateAdapter())
        .add(LocalTimeAdapter())
        .add(LocalDateTimeAdapter())
        .build()
  }
}