package data.foo

import repository.foo.FooRepository
import javax.inject.Inject

class DefaultFooRepository @Inject constructor(
  private val fooRemoteApi: FooRemoteApi
) : FooRepository {

  override fun getFoo(fooId: String) = fooRemoteApi.getFoo(fooId)

  override fun getFoos() = fooRemoteApi.getFoos()

}