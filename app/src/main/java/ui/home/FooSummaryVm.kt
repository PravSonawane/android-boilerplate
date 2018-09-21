package ui.home

import android.app.Application
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import model.foo.Foo

data class FooSummaryVm(
  val foo: Foo,
  val application: Application
) {

  private val eventSubject = PublishSubject.create<Event>()!!

  fun onClick(view: View) {
    eventSubject.onNext(Event.OnShowEventDetail(foo))
  }

  fun events(): Observable<Event> = eventSubject.hide()

  sealed class Event {
      class OnShowEventDetail(val foo: Foo): Event()
  }
}