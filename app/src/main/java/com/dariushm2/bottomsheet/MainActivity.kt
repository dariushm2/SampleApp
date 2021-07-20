package com.dariushm2.bottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.dariushm2.bottomsheet.databinding.ActivityMainBinding
import com.dariushm2.bottomsheet.navigation.setupWithNavController


class MainActivity : AppCompatActivity() {//, NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        //binding.bottomNavigation.setOnItemSelectedListener(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //navController = findNavController(R.id.nav_host_fragment)

        val controller = binding.bottomNavigation.setupWithNavController(
            listOf(
                R.navigation.nav_graph_home,
                R.navigation.nav_graph_mybets,
                R.navigation.nav_graph_live
            ),
            supportFragmentManager,
            R.id.nav_host_fragment,
            intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this) { navController ->
            //setupActionBarWithNavController(navController)
            binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        }
        currentNavController = controller


        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.homeFragment, R.id.homeFragment)
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)


        binding.fab.setOnClickListener {
            showBottomSheetDialogFragment()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        //return navController.navigateUp(appBarConfiguration)
        return currentNavController?.value?.navigateUp() ?: false
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    private fun showBottomSheetDialogFragment() {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        val deeplinkExtras = when (item.itemId) {
//            R.id.menuHome -> DeeplinkExtras.MainTabs.Home("Home")
//            R.id.menuMyBets -> DeeplinkExtras.MainTabs.MyBets("My Bets")
//            R.id.menuLive -> DeeplinkExtras.MainTabs.Live("Live")
//            else -> null
//        }
//        val deeplink = Deeplink("url", deeplinkExtras)
//        findNavController(R.id.nav_host_fragment).navigate(deeplink)
//        return true
//    }
}