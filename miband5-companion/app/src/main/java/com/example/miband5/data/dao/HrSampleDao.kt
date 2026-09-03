package com.example.miband5.data.dao
import androidx.room.*
import com.example.miband5.data.entity.HrSample
@Dao interface HrSampleDao {
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun insert(sample:HrSample)
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun insertAll(samples:List<HrSample>)
 @Query("SELECT * FROM hr_samples WHERE timestamp >= :from AND timestamp <= :to ORDER BY timestamp ASC") suspend fun getRange(from:Long,to:Long):List<HrSample>
 @Query("DELETE FROM hr_samples WHERE timestamp < :cutoff") suspend fun deleteOlderThan(cutoff:Long)
 @Query("SELECT COUNT(*) FROM hr_samples") suspend fun count():Int
}
