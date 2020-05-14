package com.example.myuniversityclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myuniversityclient.databinding.ActivityMainBinding
import com.example.myuniversityclient.domain.MainActivityViewModel
import com.example.myuniversityclient.ui.models.ShortUserInfoModel
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger DI
        (applicationContext as MainApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        // Setup
        setupMainViews()
        setupDrawerMenu()
        subscribeToViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupMainViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.drawerLayout.toolbar)
    }

    private fun setupDrawerMenu() {
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations,
        // which is important for burger-menu button appearance (it will otherwise
        // look as a back button)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_profile,
            R.id.nav_catering,
            R.id.nav_reference,
            R.id.nav_electives,
            R.id.nav_it_services
        ), binding.drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun subscribeToViewModel() {
        viewModel.shortUserInfo.observe(this, Observer(::onUserInfoDidUpdate))
    }

    private fun onUserInfoDidUpdate(result: Result<ShortUserInfoModel?>) {
        val header = binding.navView.getHeaderView(0)

        result.fold({
            if (it != null) {
                // Received info, update the view
                header.studentName.text = it.name
                header.studentEmail.text = it.email
                Glide.with(this)
                    .load(it.avatarURL)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(header.imageView)
            } else {
                // Received success with null,
                // meaning we have no auth, so re-auth
                onAuthNeeded()
            }
        }, {
            // received error
            header.studentName.text = resources.getString(R.string.user_info_error_title)
            header.studentEmail.text = it.localizedMessage ?: ""
            return // котлин мразь, без этого не компилит
        })
    }

    /**
     * TODO: Дильшат и Сергей пилят тут отображение логина
     */
    private fun onAuthNeeded() { }
}
