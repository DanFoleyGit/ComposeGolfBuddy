package com.example.composegolfbuddy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club_table")
data class Club (
    @PrimaryKey @ColumnInfo(name = "club_name") val clubName: String,
    @ColumnInfo(name = "first_brand") val clubBrand: String,
    @ColumnInfo(name = "club_loft") val clubLoft: String,
    @ColumnInfo(name = "distance") val distance: String,
    @ColumnInfo(name = "distance2") val distance2: String,
    @ColumnInfo(name = "distance3") val distance3: String,
)