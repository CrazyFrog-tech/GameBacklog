package com.example.gamebacklog.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.database.GameCardRepository
import com.example.gamebacklog.model.GameCard

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val gameCards = arrayListOf<GameCard>()
    private val gameCardAdapter = GameCardAdapter(gameCards);
    private lateinit var gameCardRepository: GameCardRepository
    private lateinit var viewModel: MainActivityViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        gameCardRepository = GameCardRepository(this)

        initViews()
        initViewModel()


    }

    private fun initViews() {
        rvCardGames.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvCardGames.adapter = gameCardAdapter
        rvCardGames.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        fab.setOnClickListener { view ->
            val intent = Intent(this, AddGame::class.java)
            startActivity(intent)        }
        createItemTouchHelper().attachToRecyclerView(rvCardGames)


    }




    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // Observe reminders from the view model, update the list when the data is changed.
        viewModel.reminders.observe(this, Observer { reminders ->
            this@MainActivity.gameCards.clear()
            this@MainActivity.gameCards.addAll(reminders)
            gameCardAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteGameCard(gameCards[position])
                gameCards.removeAt(position)
                gameCardAdapter.notifyDataSetChanged()
            }

        }
        return ItemTouchHelper(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete_shopping_list -> { viewModel.deleteReminders()
                true}
            else -> super.onOptionsItemSelected(item)
        }
    }
}
