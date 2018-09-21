package ui.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.ObservableField
import android.os.Bundle
import androidx.navigation.NavController
import com.companyname.boilerplate.R
import core.Result.GetList.OnError
import core.Result.GetList.OnSuccess
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import model.foo.Foo
import ui.foo.FooDetailFragment
import ui.home.HomeVm.State.ERROR
import ui.home.HomeVm.State.INIT
import ui.home.HomeVm.State.SUCCESS
import ui.utils.plusAssign
import usecases.foo.GetFoosUc
import javax.inject.Inject

class HomeVm @Inject constructor(
  val app: Application,
  private val getFoosUc: GetFoosUc
) : AndroidViewModel(app), LifecycleObserver {

  val state = ObservableField<State>(INIT)
  private val compositeDisposable = CompositeDisposable()
  var homeAdapter: HomeAdapter? = HomeAdapter()
  var navController: NavController? = null

  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  fun onStart() {
    homeAdapter = HomeAdapter()
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
    val vms = convertToVm(data)
    subscribeForEvents(vms)
    addToAdapter(vms)
  }

  fun onError(throwable: Throwable) {
    state.set(ERROR)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun onStop() {
    compositeDisposable.clear()
    navController = null
    homeAdapter = null
  }

  private fun convertToVm(data: List<Foo>): List<FooSummaryVm> {
    return data.map { FooSummaryVm(it, app) }
  }

  private fun subscribeForEvents(vms: List<FooSummaryVm>) {
    compositeDisposable += Observable.merge(vms.map { it.events() })
            .subscribe {
              when(it) {
                is FooSummaryVm.Event.OnShowEventDetail -> navigateToDetail(it.foo)
              }
            }
  }

  private fun navigateToDetail(foo: Foo) {
    val arguments = Bundle()
    arguments.putString(FooDetailFragment.ARG_FOO_ID, foo.id)
    navController?.navigate(R.id.action_home_to_fooDetail, arguments)
  }

  private fun addToAdapter(vms: List<FooSummaryVm>) {
    homeAdapter?.clear()
    homeAdapter?.add(vms)
    homeAdapter?.notifyDataSetChanged()
  }

  enum class State {
    INIT,
    SUCCESS,
    LOADING,
    ERROR
  }
}
