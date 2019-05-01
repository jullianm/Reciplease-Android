package com.jullianm.reciplease.recipes

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jullianm.reciplease.model.RecipeModel

@Database(entities = [RecipeModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}