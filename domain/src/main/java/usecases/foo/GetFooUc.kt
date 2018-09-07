package usecases.foo

import core.Result.GetOne
import io.reactivex.Observable
import model.foo.Foo
import repository.foo.FooRepository
import javax.inject.Inject

class GetFooUc @Inject constructor(
  private val fooRepository: FooRepository
) {

  fun execute(fooId: String): Observable<GetOne<Foo>> {
    return fooRepository.getFoo(fooId)
  }
}