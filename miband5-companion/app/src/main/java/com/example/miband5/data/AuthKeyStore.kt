package com.example.miband5.data
import android.content.Context
class AuthKeyStore(context:Context){
 private val prefs=context.getSharedPreferences("miband5_auth",Context.MODE_PRIVATE)
 var authKey:ByteArray? get()=prefs.getString(KEY_AUTH,null)?.hexToBytes() set(v){prefs.edit().putString(KEY_AUTH,v?.toHex()).apply()}
 var lastDeviceAddress:String? get()=prefs.getString(KEY_DEVICE,null) set(v){prefs.edit().putString(KEY_DEVICE,v).apply()}
 private fun ByteArray.toHex()=joinToString(""){"%02x".format(it)}
 private fun String.hexToBytes()=chunked(2).map{it.toInt(16).toByte()}.toByteArray()
 companion object{private const val KEY_AUTH="auth_key_hex";private const val KEY_DEVICE="last_device_address"}
}
