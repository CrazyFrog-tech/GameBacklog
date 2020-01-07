package com.example.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gamebacklog.model.GameCard

@Dao
interface GameCardDao {
@Insert
suspend fun insertGameCard(gameCard: GameCard)

@Delete
fun deleteGameCard(gameCard: GameCard)

@Update
fun updateGameCard(gameCard: GameCard)

@Query("SELECT * FROM gameCardTable ORDER BY date DESC")
 fun getAllGames(): LiveData<List<GameCard>>

 @Query("DELETE FROM gameCardTable")
 suspend fun deleteAllGames()


}