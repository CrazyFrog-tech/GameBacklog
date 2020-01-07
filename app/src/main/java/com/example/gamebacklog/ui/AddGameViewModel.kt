package com.example.gamebacklog.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.database.GameCardRepository
import com.example.gamebacklog.model.GameCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGameViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val reminderRepository = GameCardRepository(application.applicationContext)

    fun insertReminder(reminder: GameCard) {
        ioScope.launch {
            reminderRepository.insertGameCard(reminder)
        }
    }
}