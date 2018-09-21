package ui.foo

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.ObservableField
import androidx.navigation.NavController
import core.Result.GetOne.OnError
import core.Result.GetOne.OnSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import model.foo.Foo
import ui.utils.plusAssign
import ui.foo.FooDetailVm.State.ERROR
import ui.foo.FooDetailVm.State.INIT
import ui.foo.FooDetailVm.State.SUCCESS
import usecases.foo.GetFooUc
import javax.inject.Inject

class FooDetailVm @Inject constructor(
  val app: Application,
  private val getFooUc: GetFooUc
) : AndroidViewModel(app), LifecycleObserver {

  var fooId: String? = null

  val foo = ObservableField<Foo>()
  val state = ObservableField<State>(INIT)

  private val compositeDisposable = CompositeDisposable()
  var navController: NavController? = null

  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  fun onStart() {
    require(!fooId.isNullOrEmpty()) { "FooDetailVm.fooId is required to be set" }

    compositeDisposable += getFooUc.execute(fooId!!)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          when (it) {
            is OnSuccess -> onSuccess(it.data)
            is OnError -> onError(it.throwable)
          }
        }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun onStop() {
    compositeDisposable.clear()
    navController = null
  }

  private fun onSuccess(data: Foo) {
    state.set(SUCCESS)
    foo.set(data)
  }

  private fun onError(throwable: Throwable) {
    state.set(ERROR)
  }

  enum class State {
    INIT,
    LOADING,
    SUCCESS,
    ERROR
  }
}
