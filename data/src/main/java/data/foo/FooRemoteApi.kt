package data.foo

import core.Result.GetList
import core.Result.GetOne
import data.foo.v1.FooApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import model.foo.Foo
import javax.inject.Inject

class FooRemoteApi @Inject constructor(private val fooApi: FooApi) {

  fun getFoos(): Observable<GetList<Foo>> {
    val foos = listOf(Foo("ID1", "Name1"), Foo("ID2", "Name2"))

    //return fooApi.getFoos()
    return Observable.just(foos) //TODO replace with actual network call
        .flatMapIterable { it }
        .map { it } //TODO map response object to domain object
        .toList()
        .toObservable()
        .map { GetList.OnSuccess(it) as GetList<Foo> }
        .onErrorReturn { GetList.OnError(it) }
        .subscribeOn(Schedulers.io())
  }

  fun getFoo(fooId: String): Observable<GetOne<Foo>> {
    val foos = listOf(Foo("ID1", "Name1"), Foo("ID2", "Name2"))
    //return fooApi.getFoo(fooId)  //TODO replace with actual network call
    return Observable.just(foos.first { it.id == fooId })
        .map { it } //TODO map response object to domain object
        .map { GetOne.OnSuccess(it) as GetOne<Foo> } //TODO handle OnNotFound
        .onErrorReturn { GetOne.OnError(it) }
        .subscribeOn(Schedulers.io())
  }
}