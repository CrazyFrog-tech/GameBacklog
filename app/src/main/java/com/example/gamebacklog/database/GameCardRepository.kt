package com.example.gamebacklog.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.GameCard

class GameCardRepository(context: Context)
{
    private val gameCardDao: GameCardDao

    init {
        val gameCardRoomDatabase = GameCardRoomDatabase.getDatabase(context)
        gameCardDao = gameCardRoomDatabase!!.gameCardDao()
    }

 /*   fun getGameCard() LiveData<GameCard?> {
    return gameCardDao.getGameCard()
}*/

    fun updateGameCard(gameCard: GameCard) {
        gameCardDao.updateGameCard(gameCard)
    }

    fun deleteGameCard(gameCard: GameCard) {
        gameCardDao.deleteGameCard(gameCard)
    }

    fun getAllGameCards() : LiveData<List<GameCard>>{
        return gameCardDao.getAllGames()
    }

    suspend fun deleteAllgameCards() {
        gameCardDao.deleteAllGames()
    }

    suspend fun insertGameCard(gameCard: GameCard) {
        gameCardDao.insertGameCard(gameCard)
    }

}