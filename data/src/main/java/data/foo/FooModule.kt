package data.foo

import dagger.Module
import dagger.Provides
import data.network.NetworkModule
import data.foo.v1.FooApi
import repository.foo.FooRepository
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class FooModule {

  @Provides fun provideFooRepository(fooApi: FooApi): FooRepository {
    return DefaultFooRepository(FooRemoteApi(fooApi))
  }

  @Provides fun provideFooApi(retrofit: Retrofit): FooApi {
    return retrofit.create(FooApi::class.java)
  }
}
