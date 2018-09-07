package repository.foo

import core.Result
import io.reactivex.Observable
import model.foo.Foo

interface FooRepository {

  fun getFoos(): Observable<Result.GetList<Foo>>

  fun getFoo(fooId: String): Observable<Result.GetOne<Foo>>
}