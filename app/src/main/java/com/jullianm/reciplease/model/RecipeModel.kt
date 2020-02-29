package com.jullianm.reciplease.model

import android.media.Image
import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.net.URL
import java.util.*

@Entity
@Parcelize
data class RecipeModel(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "ingredients") val ingredients: List<String>,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "instructions") val instructions: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
): Parcelable


