package com.dariushm2.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dariushm2.bottomsheet.databinding.ActivityMainBinding
import com.dariushm2.bottomsheet.navigation.Deeplink
import com.dariushm2.bottomsheet.navigation.DeeplinkExtras
import com.dariushm2.bottomsheet.navigation.DestinationFactory
import com.dariushm2.bottomsheet.navigation.navigate
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {//, NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.toolbar)

        //binding.bottomNavigation.setOnItemSelectedListener(this)

        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = findNavController(R.id.nav_host_fragment)

        binding.bottomNavigation.setupWithNavController(navController)
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.homeFragment, R.id.myBetsFragment, R.id.liveFragment)
//        )
        //binding.toolbar.setupWithNavController(navController, appBarConfiguration)


        binding.fab.setOnClickListener {
            showBottomSheetDialogFragment()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
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