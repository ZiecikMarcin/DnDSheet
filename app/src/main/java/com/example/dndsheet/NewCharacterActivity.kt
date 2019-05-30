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
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner

class NewCharacterActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var chosenRace: String
    lateinit var chosenSubrace: String
    lateinit var chosenBackground: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        ////


        val raceSpinner: Spinner = findViewById(R.id.chosen_race)
        ArrayAdapter.createFromResource(this, R.array.races_array, android.R.layout.simple_spinner_item).
                also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                raceSpinner.adapter = adapter}

        val subraceSpinner: Spinner = findViewById(R.id.chosen_subrace)
        val backgroundSpinner: Spinner = findViewById(R.id.chosen_background)
        ArrayAdapter.createFromResource(this, R.array.character_backgrounds, android.R.layout.simple_spinner_item).
                also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                backgroundSpinner.adapter = adapter}
        val elfAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.subraces_elf, android.R.layout.simple_spinner_item )
        val dwarfAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.subraces_dwarf, android.R.layout.simple_spinner_item )

        setupButtons()

        raceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (raceSpinner.selectedItem.toString()){
                    "Elf" ->  subraceSpinner.adapter = elfAdapter
                    "Dwarf" -> subraceSpinner.adapter = dwarfAdapter
                }
                chosenRace = raceSpinner.selectedItem.toString()
            }
        }

        subraceSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                chosenSubrace = subraceSpinner.selectedItem.toString()
            }
        }

        backgroundSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                chosenBackground = backgroundSpinner.selectedItem.toString()
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
        menuInflater.inflate(R.menu.main, menu)
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
                finish()
            }
            R.id.nav_new -> {
                val intent = Intent(this, NewCharacterActivity::class.java)
                startActivity(intent)
                finish()
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

    private fun setupButtons(){
        val raceInfoButton: ImageButton = findViewById(R.id.race_description_imageButton)
        val subraceInfoButton: ImageButton = findViewById(R.id.subrace_description_imageButton)
        val backgroundInfoButton: ImageButton = findViewById(R.id.background_description_imageButton)
        raceInfoButton.setOnClickListener{
            raceDescription()
        }
        subraceInfoButton.setOnClickListener{
            subraceDescription()
        }
        backgroundInfoButton.setOnClickListener{
            backgroundDescription()
        }
    }

    private fun raceDescription(){
        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setTitle("Race description")
            setMessage(getChosenRace())
            setNeutralButton("OK"){_, _ ->  }
            show()
        }
    }

    private fun subraceDescription(){
        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setTitle("Subrace description")
            setMessage(getChosenSubrace())
            setNeutralButton("OK"){_, _ ->  }
            show()
        }
    }

    private fun backgroundDescription(){
        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setTitle("Background description")
            setMessage(getChosenBackground())
            setNeutralButton("OK"){_, _ ->  }
            show()
        }
    }

    private fun getChosenRace(): Int{
        return when (chosenRace){
            "Elf" -> R.string.elf
            "Dwarf" -> R.string.dwarf
            else -> 0
        }
    }

    private fun getChosenSubrace(): Int{
        return when (chosenSubrace){
            "Wood Elf" -> R.string.wood_elf
            "Hill Dwarf" -> R.string.hill_dwarf
            else -> 0
        }
    }

    private fun getChosenBackground(): Int{
        return when (chosenBackground){
            "Acolyte" -> R.string.acolyte_bg
            "Criminal" -> R.string.criminal_bg
            else -> 0
        }
    }
}

