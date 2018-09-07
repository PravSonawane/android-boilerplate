package ui.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.ObservableField
import androidx.navigation.NavController
import core.Result.GetList.OnError
import core.Result.GetList.OnSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import model.foo.Foo
import ui.home.HomeVm.State.ERROR
import ui.home.HomeVm.State.INIT
import ui.home.HomeVm.State.SUCCESS
import ui.utils.plusAssign
import usecases.foo.GetFoosUc
import javax.inject.Inject

class HomeVm @Inject constructor(
  val app: Application,
  val homeAdapter: HomeAdapter,
  private val getFoosUc: GetFoosUc
) : AndroidViewModel(app), LifecycleObserver {

  val state = ObservableField<State>(INIT)

  private val compositeDisposable = CompositeDisposable()
  private lateinit var navController: NavController

  fun setNavController(navController: NavController) {
    this.navController = navController
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  fun onStart() {
    compositeDisposable += getFoosUc.execute()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          when (it) {
            is OnSuccess -> onSuccess(it.data)
            is OnError -> onError(it.throwable)
          }
        }
  }

  fun onSuccess(data: List<Foo>) {
    state.set(SUCCESS)
    addToAdapter(data)
  }

  fun onError(throwable: Throwable) {
    state.set(ERROR)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun onStop() {
    compositeDisposable.clear()
  }

  private fun addToAdapter(data: List<Foo>) {
    homeAdapter.clear()
    data.forEach { homeAdapter.add(FooSummaryVm(it, app, navController)) }
    homeAdapter.notifyDataSetChanged()
  }

  enum class State {
    INIT,
    SUCCESS,
    LOADING,
    ERROR
  }
}
