package com.example.gamebacklog.ui


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.database.GameCardRepository
import com.example.gamebacklog.model.GameCard

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val reminderRepository = GameCardRepository(application.applicationContext)

    val reminders: LiveData<List<GameCard>> = reminderRepository.getAllGameCards()

//    fun insertReminder(reminder: Reminder) {
//        ioScope.launch {
//            reminderRepository.insertReminder(reminder)
//        }
//    }

    fun deleteReminders() {
        ioScope.launch {
            reminderRepository.deleteAllgameCards()
        }
    }

    fun deleteGameCard(gamecard: GameCard) {
        ioScope.launch {
            reminderRepository.deleteGameCard(gamecard)
        }
    }

}