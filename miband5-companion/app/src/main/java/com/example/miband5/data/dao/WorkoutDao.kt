package com.example.miband5.data.dao
import androidx.room.*
import com.example.miband5.data.entity.Workout
@Dao interface WorkoutDao {
 @Insert suspend fun insert(workout:Workout)
 @Query("SELECT * FROM workouts ORDER BY start_time DESC") suspend fun getAll():List<Workout>
 @Query("SELECT * FROM workouts WHERE start_time BETWEEN :from AND :to ORDER BY start_time ASC") suspend fun getRange(from:Long,to:Long):List<Workout>
}
