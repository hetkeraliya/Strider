package com.example.miband5.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="hr_samples") data class HrSample(@PrimaryKey val timestamp:Long,val heartRate:Int,val rawIntensity:Int=0)
