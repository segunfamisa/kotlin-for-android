package com.segunfamisa.kotlin.android.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.segunfamisa.kotlin.android.App
import org.jetbrains.anko.db.*

class ForecastDbHelper(ctx: Context = App.instance) :
        ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper()}
    }

    override fun onCreate(db: SQLiteDatabase) {
        // create city forecast table
        db.createTable(CityForecastTable.NAME, true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT)

        db.createTable(DayForecastTable.NAME, true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }
}