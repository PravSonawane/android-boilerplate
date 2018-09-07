package ui.home


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
import com.merryapps.placehunt.databinding.FragmentHomeBinding
import ui.main.MainActivity
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory).get(HomeVm::class.java)
    }
    private val homeComponent: HomeComponent by lazy {
        DaggerHomeComponent.builder()
                .activityComponent((activity as MainActivity).mainComponent)
                .homeModule(HomeModule())
                .build()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home,
                container, false)

        homeComponent.injectIn(this)

        //FIXME Find better solution. navController needs to be set every time as it is associated with the fragment instance
        viewModel.setNavController(findNavController())
        lifecycle.addObserver(viewModel)
        binding.vm = viewModel

        return binding.root
    }


}
