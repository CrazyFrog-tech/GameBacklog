package com.example.gamebacklog.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.gamebacklog.R
import com.example.gamebacklog.database.GameCardRepository
import com.example.gamebacklog.model.GameCard

import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class AddGame : AppCompatActivity() {


    private lateinit var viewModel: AddGameViewModel


    private lateinit var gameCardRepository: GameCardRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(AddGameViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        setSupportActionBar(toolbar)
        gameCardRepository = GameCardRepository(this)


        fab.setOnClickListener { view ->
            OnSaveClick()
        }
    }

    private fun OnSaveClick() {
        var title = etTitle.text
        var platform = etPlatform.text
//        var date = ("${etDay.text.toString().toInt()} ${etMonth.text} ${etYear.text}")
        var gameCard: GameCard
        var date: String = ("${etYear.text.toString()}-${etMonth.text.toString()}-${etDay.text.toString()}");
        print(date.toString() + "HIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII")
        gameCard =
            GameCard(title.toString(), platform.toString(),
                SimpleDateFormat("yyyy-MM-dd").parse(date)
            )
        print(date.toString())

        CoroutineScope(Dispatchers.Main).launch {

            val reminders = withContext(Dispatchers.IO) {
                viewModel.insertReminder(gameCard)
            }


        }

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)


    }
}