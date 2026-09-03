package com.example.miband5.ble

object MiBand5DataParser {
    data class WalkData(val steps: Int, val distanceMeters: Int, val calories: Int)
    fun parseWalk(data: ByteArray): WalkData? {
        if (data.size < 12) return null
        return WalkData(leInt(data,0), leInt(data,4), leInt(data,8))
    }
    fun parseHeartRate(data: ByteArray): Int? {
        if (data.isEmpty()) return null
        val flags=data[0].toInt() and 0xFF
        return if(flags and 1 != 0) { if(data.size<3)null else (data[1].toInt() and 255) or ((data[2].toInt() and 255) shl 8) } else { if(data.size<2)null else data[1].toInt() and 255 }
    }
    private fun leInt(data:ByteArray,offset:Int):Int=(data[offset].toInt() and 255) or ((data[offset+1].toInt() and 255) shl 8) or ((data[offset+2].toInt() and 255) shl 16) or ((data[offset+3].toInt() and 255) shl 24)
}
