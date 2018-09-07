package ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.companyname.boilerplate.R
import ui.app.BoilerplateApp

class MainActivity : AppCompatActivity() {

    val mainComponent: MainComponent by lazy {
        DaggerMainComponent.builder()
                .appComponent((application as BoilerplateApp).appComponent)
                .mainModule(MainModule(this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //dagger injection
        mainComponent.injectIn(this)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()
}
