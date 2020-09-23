package com.seniorlibs.thirdlib.mmkv

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.tencent.mmkv.MMKV

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/8/21.
 * Mender:
 * Modify:
 * Description: MMKV——基于mmap的高性能通用key-value组件，替代SharedPreferences
 */
class SpUtils @SuppressLint("CommitPrefEdits") private constructor() {

    companion object {
        // 证明MMKV每一个id对应一个文件
        val baseKv = MMKV.mmkvWithID("mmkv_base_id")
        val loginKv = MMKV.mmkvWithID("mmkv_login_id")
        val mainKv = MMKV.mmkvWithID("mmkv_mian_id")

        @JvmStatic
        val instance: SpUtils
            get() = SingletonHolder.instance
    }

    private object SingletonHolder {
        val instance = SpUtils()
    }

    val mKv: MMKV

    private val mEditor: SharedPreferences.Editor

    init {
        // 根据业务区别存储, 附带一个自己的 ID
        mKv = MMKV.mmkvWithID("mmkv_id")
        mEditor = mKv.edit()
    }


    private fun save(mEditor: SharedPreferences.Editor) {
        mEditor.apply()
    }

    fun write(key: String?, defValue: Int) {
        mEditor.putInt(key, defValue)
        save(mEditor)
    }

    fun write(key: String?, defValue: Long) {
        mEditor.putLong(key, defValue)
        save(mEditor)
    }

    fun write(key: String?, defValue: Boolean) {
        mEditor.putBoolean(key, defValue)
        save(mEditor)
    }

    fun write(key: String?, defValue: String?) {
        mEditor.putString(key, defValue)
        save(mEditor)
    }

    fun readInt(key: String?): Int {
        return mKv.getInt(key, 0)
    }

    fun readInt(key: String?, defValue: Int): Int {
        return mKv.getInt(key, defValue)
    }

    fun readLong(key: String?, defValue: Long): Long {
        return mKv.getLong(key, defValue)
    }

    fun readBoolean(key: String?): Boolean {
        return mKv.getBoolean(key, false)
    }

    fun readBoolean(
        k: String?, defBool: Boolean
    ): Boolean {
        return mKv.getBoolean(k, defBool)
    }

    fun readString(key: String?): String? {
        return mKv.getString(key, null)
    }

    fun readString(key: String?, defValue: String?): String? {
        return mKv.getString(key, defValue)
    }

    fun readAll(): Map<String, *> {
        return mKv.all
    }

    fun remove(key: String?) {
        val mEditor = mKv.edit()
        mEditor.remove(key)
        save(mEditor)
    }

    fun clean() {
        val mEditor = mKv.edit()
        mEditor.clear()
        save(mEditor)
    }
}