package com.example.littlelemon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.littlelemon.data.remote.Menu
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.Companion.ABORT, entity = Menu::class)
    suspend  fun addMenu(menu: Menu)

    @Query("SELECT * FROM menus")
    fun getAllMenus(): List<Menu>

    @Query("SELECT * FROM menus WHERE category = :category")
    fun getMenusByCategory(category: String): Flow<List<Menu>>

}