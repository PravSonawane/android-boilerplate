package usecases.foo

import core.Result.GetList
import io.reactivex.Observable
import model.foo.Foo
import repository.foo.FooRepository
import javax.inject.Inject

class GetFoosUc @Inject constructor(
  private val fooRepository: FooRepository
) {

  fun execute(): Observable<GetList<Foo>> {
    return fooRepository.getFoos()
  }
}