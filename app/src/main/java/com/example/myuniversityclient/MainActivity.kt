package com.example.myuniversityclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myuniversityclient.di.ApplicationComponent
import com.example.myuniversityclient.domain.MainActivityViewModel
import com.example.myuniversityclient.ui.models.ShortUserInfoModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.nav_header_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
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
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun setupDrawerMenu() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
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
            R.id.nav_internships,
            R.id.nav_it_services
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun subscribeToViewModel() {
        viewModel.getShortUserInfo().observe(this, Observer(::onUserInfoDidUpdate))
    }

    private fun onUserInfoDidUpdate(result: Result<ShortUserInfoModel?>) {
        val navView: NavigationView = findViewById(R.id.nav_view)
        val header = navView.getHeaderView(0)
        val nameText: TextView = header.findViewById(R.id.studentName)
        val emailText: TextView = header.findViewById(R.id.studentEmail)
        val avatarView: ImageView = header.findViewById(R.id.imageView)

        result.fold({
            if (it != null) {
                // Received info, update the view
                nameText.text = it.name
                emailText.text = it.email
                Glide.with(this)
                    .load(it.avatarURL)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(avatarView)
            } else {
                // Received success with null,
                // meaning we have no auth, so re-auth
                onAuthNeeded()
            }
        }, {
            // received error
            nameText.text = resources.getString(R.string.user_info_error_title)
            emailText.text = it.localizedMessage
        })
    }

    /**
     * TODO: Дильшат и Сергей пилят тут отображение логина
     */
    private fun onAuthNeeded() { }
}
