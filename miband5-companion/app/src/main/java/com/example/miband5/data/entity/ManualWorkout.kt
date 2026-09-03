package com.example.miband5.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="manual_workouts") data class ManualWorkout(@PrimaryKey(autoGenerate=true) val id:Long=0,val date:String,val label:String,val exercises:String="[]")
