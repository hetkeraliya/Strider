package com.example.miband5.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="devices") data class Device(@PrimaryKey(autoGenerate=true) val id:Long=0,val address:String,val name:String="Mi Band 5",val lastSeen:Long=0)
