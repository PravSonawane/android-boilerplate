package ui.home

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import com.merryapps.placehunt.R
import model.foo.Foo
import ui.foo.FooDetailFragment.Companion.ARG_FOO_ID

data class FooSummaryVm(
  val foo: Foo,
  val application: Application,
  private val navController: NavController
) {

  fun onClick(view: View) {
    val arguments = Bundle()
    arguments.putString(ARG_FOO_ID, foo.id)
    navController.navigate(R.id.action_home_to_fooDetail, arguments)
  }
}