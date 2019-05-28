package com.example.dndsheet

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_character_list.*
import kotlinx.android.synthetic.main.activity_character_list.toolbar
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_character_list.*

class CharacterListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val characterList: ArrayList<String> = ArrayList()
        characterList.add("Cacao Decomoreno")
        rv_character_list.layoutManager = LinearLayoutManager(this)
        rv_character_list.adapter = CharacterAdapter(characterList, this)
    }

}
