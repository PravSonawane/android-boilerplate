package ui.home

import android.app.Application
import android.content.res.Resources
import androidx.navigation.NavController
import core.Result.GetList.OnError
import core.Result.GetList.OnSuccess
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import ui.helpers.createDummyFoo
import ui.home.HomeVm.State.ERROR
import ui.home.HomeVm.State.SUCCESS
import usecases.foo.GetFoosUc
import java.io.IOException

class HomeVmTests {

  @get:Rule val schedulerRule = RxSchedulerRule()

  private lateinit var mockApplication: Application
  private lateinit var mockResources: Resources
  private lateinit var mockHomeAdapter: HomeAdapter
  private lateinit var mockGetFoosUc: GetFoosUc
  private lateinit var mockNavController: NavController

  @Before
  fun before() {
    mockApplication = mock(Application::class.java)
    mockResources = mock(Resources::class.java)
    mockHomeAdapter = mock(HomeAdapter::class.java)
    mockGetFoosUc = mock(GetFoosUc::class.java)
    mockNavController = mock(NavController::class.java)

    Mockito.`when`(mockApplication.resources).thenReturn(mockResources)
  }

  @Test
  fun `GIVEN a successful foo retrieval WHEN view model receives an onStart callback THEN view model state is SUCCESS and passes data to adapter`() {
    val foo1 = createDummyFoo("1")
    val foo2 = createDummyFoo("2")

    //GIVEN
    `when`(mockGetFoosUc.execute()).thenReturn(Observable.just(OnSuccess(listOf(foo1, foo2))))

    val homeVm = HomeVm(mockApplication, mockHomeAdapter, mockGetFoosUc)
    homeVm.setNavController(mockNavController)

    //WHEN
    homeVm.onStart()

    //THEN
    assert(homeVm.state.get() == SUCCESS)
    val fooSummaryVm1 = FooSummaryVm(foo1, mockApplication, mockNavController)
    val fooSummaryVm2 = FooSummaryVm(foo2, mockApplication, mockNavController)
    verify(mockHomeAdapter).add(fooSummaryVm1)
    verify(mockHomeAdapter).add(fooSummaryVm2)
    verify(homeVm.homeAdapter).notifyDataSetChanged()
  }

  @Test
  fun `GIVEN an unsuccessful foo retrieval WHEN view model receives an onStart callback THEN view model state is error`() {
    //GIVEN
    `when`(mockGetFoosUc.execute()).thenReturn(Observable.just(OnError(IOException())))

    val homeVm = HomeVm(mockApplication, mockHomeAdapter, mockGetFoosUc)
    homeVm.setNavController(mockNavController)

    //WHEN
    homeVm.onStart()

    //THEN
    assert(homeVm.state.get() == ERROR)
  }
}