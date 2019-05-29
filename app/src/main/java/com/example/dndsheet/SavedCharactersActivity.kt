package com.example.dndsheet

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.ListView
import android.widget.Toast

class SavedCharactersActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_characters)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        listView = findViewById(R.id.listView)
        var characterList = mutableListOf<Model>()
        characterList.add(Model("Cacao Decomoreno","Sorcerer lvl 5", R.mipmap.ic_launcher))
        characterList.add(Model("Cacao Decomoreno","Sorcerer lvl 5", R.mipmap.ic_launcher))
        characterList.add(Model("Cacao Decomoreno","Sorcerer lvl 5", R.mipmap.ic_launcher))
        characterList.add(Model("Cacao Decomoreno","Sorcerer lvl 5", R.mipmap.ic_launcher))
        listView.adapter = CharacterAdapter(this, R.layout.character_list_item, characterList)


        listView.setOnItemClickListener { parent, view, position, id ->

            if (position == 0) {
                Toast.makeText(this@SavedCharactersActivity, "Item One", Toast.LENGTH_SHORT).show()
            }
            if (position == 1) {
                Toast.makeText(this@SavedCharactersActivity, "Item Two", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.saved_characters, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_saved_characters -> {
                val intent = Intent(this, SavedCharactersActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_new -> {
                val intent = Intent(this, NewCharacterActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
