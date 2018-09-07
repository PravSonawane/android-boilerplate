package data.foo.v1

import io.reactivex.Observable
import model.foo.Foo
import retrofit2.http.GET
import retrofit2.http.Path

interface FooApi {

  @GET("/api/foos/")
  fun getFoos(): Observable<List<Foo>>

  @GET("/api/foos/{fooId}")
  fun getFoo(@Path("fooId") fooId: String): Observable<Foo>
}