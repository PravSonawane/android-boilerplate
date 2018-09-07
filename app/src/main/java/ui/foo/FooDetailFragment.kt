package ui.foo


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.merryapps.placehunt.R
import com.merryapps.placehunt.databinding.FragmentFooDetailBinding
import ui.main.MainActivity
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class FooDetailFragment : Fragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private val viewModel by lazy {
    ViewModelProviders.of(activity!!, viewModelFactory).get(FooDetailVm::class.java)
  }
  private val fooDetailComponent: FooDetailComponent by lazy {
    DaggerFooDetailComponent.builder()
        .activityComponent((activity as MainActivity).mainComponent)
        .fooDetailModule(FooDetailModule())
        .build()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val binding = DataBindingUtil.inflate<FragmentFooDetailBinding>(inflater, R.layout.fragment_foo_detail,
        container, false)

    fooDetailComponent.injectIn(this)

    //FIXME Find better solution. navController needs to be set every time as it is associated with the fragment instance
    viewModel.setNavController(findNavController())
    lifecycle.addObserver(viewModel)

    viewModel.fooId = arguments?.getString(ARG_FOO_ID)
    binding.vm = viewModel

    return binding.root
  }

  companion object {
    const val ARG_FOO_ID = "FOO_ID"
  }

}
