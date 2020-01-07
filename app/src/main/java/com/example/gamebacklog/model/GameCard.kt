package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "gameCardTable")
class GameCard (
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "platform")
    var platform: String,
    @ColumnInfo(name = "date")
    var date: Date,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
): Parcelable