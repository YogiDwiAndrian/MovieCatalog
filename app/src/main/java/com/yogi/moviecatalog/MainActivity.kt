package com.yogi.moviecatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.yogi.moviecatalog.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    private var twiceExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.app_name, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        val searchMovie: MenuItem? = menu.findItem(R.id.item_search_movie)
        val searchViewMovie: SearchView = searchMovie?.actionView as SearchView
        searchViewMovie.queryHint = resources.getString(R.string.menu_search)
        searchViewMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val sendQueryIntent = Intent(this@MainActivity, SearchActivity::class.java)
                sendQueryIntent.putExtra(
                    SearchActivity.SEARCH_KEY,
                    SearchActivity.SEARCH_MOVIE_KEY
                )
                sendQueryIntent.putExtra(SearchActivity.SEARCH_MOVIE_KEY, query)
                startActivity(sendQueryIntent)
                Log.d("TES123", "Query berada di Movie")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        val searchTvShow: MenuItem? = menu.findItem(R.id.item_search_tv_show)
        val searchViewTvShow: SearchView = searchTvShow?.actionView as SearchView
        searchViewTvShow.queryHint = getString(R.string.menu_search)
        searchViewTvShow.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val sendQueryIntent = Intent(this@MainActivity, SearchActivity::class.java)
                sendQueryIntent.putExtra(
                    SearchActivity.SEARCH_KEY,
                    SearchActivity.SEARCH_TV_SHOW_KEY
                )
                sendQueryIntent.putExtra(SearchActivity.SEARCH_TV_SHOW_KEY, query)
                startActivity(sendQueryIntent)
                Log.d("TES123", "Query berada di Tv Show")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_bookmark -> {
                Toast.makeText(this, R.string.menu_bookmark, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_language -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
            R.id.nav_pro_version -> {
                Toast.makeText(this, R.string.menu_pro_version, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_rate -> {
                Toast.makeText(this, R.string.menu_rate, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_feedback -> {
                Toast.makeText(this, R.string.menu_feedback, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_share -> {
                Toast.makeText(this, R.string.menu_share, Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    public override fun onDestroy() {
        super.onDestroy()
    }

    public override fun onPause() {
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
    }

    public override fun onRestart() {
        super.onRestart()
    }

    override fun onBackPressed() {
        if (twiceExit) {
            super.onBackPressed()
            return
        }

        this.twiceExit = true
        Toast.makeText(this, R.string.exittap, Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ twiceExit = false }, 2000)
    }
}
