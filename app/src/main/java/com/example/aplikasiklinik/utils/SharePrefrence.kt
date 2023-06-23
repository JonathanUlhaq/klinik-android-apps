package com.example.aplikasiklinik.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharePrefrence @Inject constructor(@ApplicationContext context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences("PREFS_TOKEN", Context.MODE_PRIVATE)

    fun saveNumber(number: String) {
        val editor = prefs.edit()
        editor.putString("USER_NUMBER", number)
        editor.apply()
    }

    fun saveNama(nama: String) {
        val editor = prefs.edit()
        editor.putString("USER_NAMA", nama)
        editor.apply()
    }

    fun saveAlamat(alamat: String) {
        val editor = prefs.edit()
        editor.putString("USER_ALAMAT", alamat)
        editor.apply()
    }

    fun saveBPJS(bpjs: String) {
        val editor = prefs.edit()
        editor.putString("USER_BPJS", bpjs)
        editor.apply()
    }

    fun saveLahir(lahir: String) {
        val editor = prefs.edit()
        editor.putString("USER_LAHIR", lahir)
        editor.apply()
    }
    fun saveTelepon(telepon: String) {
        val editor = prefs.edit()
        editor.putString("USER_TELEPON", telepon)
        editor.apply()
    }


    fun getTelepon(): String? {
        return prefs.getString("USER_TELEPON", null)
    }
    fun getLahir(): String? {
        return prefs.getString("USER_LAHIR", null)
    }
    fun getBpjs(): String? {
        return prefs.getString("USER_BPJS", null)
    }
    fun getAlamat(): String? {
        return prefs.getString("USER_ALAMAT", null)
    }
    fun getNama(): String? {
        return prefs.getString("USER_NAMA", null)
    }

    fun getNumber(): String? {
        return prefs.getString("USER_NUMBER", null)
    }
}