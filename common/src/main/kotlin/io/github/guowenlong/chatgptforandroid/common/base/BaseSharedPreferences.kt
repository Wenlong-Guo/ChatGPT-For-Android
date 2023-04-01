package io.github.guowenlong.chatgptforandroid.common.base

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.koin.android.BuildConfig
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Description: [SharedPreferences]的基类
 * Author:      郭文龙
 * Date:        2023/3/30 0:16
 * Email:       guowenlong20000@sina.com
 */

open class BaseSharedPreferences {

    companion object {
        val debug = if (BuildConfig.DEBUG) "debug" else ""
    }

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    object Delegates {
        fun boolean(defaultValue: Boolean = false) =
            object : ReadWriteProperty<BaseSharedPreferences, Boolean> {
                override fun getValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>
                ): Boolean {
                    return thisRef.preferences.getBoolean(property.name + debug, defaultValue)
                }

                override fun setValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>,
                    value: Boolean
                ) {
                    thisRef.preferences.edit().putBoolean(property.name + debug, value).commit()
                }
            }

        fun float(defaultValue: Float = 0.0f) =
            object : ReadWriteProperty<BaseSharedPreferences, Float> {
                override fun getValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>
                ): Float {
                    return thisRef.preferences.getFloat(property.name + debug, defaultValue)
                }

                override fun setValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>,
                    value: Float
                ) {
                    thisRef.preferences.edit().putFloat(property.name + debug, value).commit()
                }
            }

        fun int(defaultValue: Int = 0) = object : ReadWriteProperty<BaseSharedPreferences, Int> {

            override fun getValue(thisRef: BaseSharedPreferences, property: KProperty<*>): Int {
                return thisRef.preferences.getInt(property.name + debug, defaultValue)
            }

            override fun setValue(
                thisRef: BaseSharedPreferences,
                property: KProperty<*>,
                value: Int
            ) {
                thisRef.preferences.edit().putInt(property.name + debug, value).commit()
            }
        }

        fun long(defaultValue: Long = 0L) =
            object : ReadWriteProperty<BaseSharedPreferences, Long> {

                override fun getValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>
                ): Long {
                    return thisRef.preferences.getLong(property.name + debug, defaultValue)
                }

                override fun setValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>,
                    value: Long
                ) {
                    thisRef.preferences.edit().putLong(property.name + debug, value).commit()
                }
            }


        fun string(defaultValue: String? = null) =
            object : ReadWriteProperty<BaseSharedPreferences, String?> {
                override fun getValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>
                ): String? {
                    return thisRef.preferences.getString(property.name + debug, defaultValue)
                }

                override fun setValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>,
                    value: String?
                ) {
                    thisRef.preferences.edit().putString(property.name + debug, value).commit()
                }
            }

        fun stringSet(defaultValue: Set<String>? = null) =
            object : ReadWriteProperty<BaseSharedPreferences, Set<String>?> {
                override fun getValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>
                ): Set<String>? {
                    return thisRef.preferences.getStringSet(property.name + debug, defaultValue)
                }

                override fun setValue(
                    thisRef: BaseSharedPreferences,
                    property: KProperty<*>,
                    value: Set<String>?
                ) {
                    thisRef.preferences.edit().putStringSet(property.name + debug, value).commit()
                }
            }
    }
}