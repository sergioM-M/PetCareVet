package com.example.petcarevet.data.local.session

import android.content.Context

class SessionDataSource(context: Context) {
    private val preferences = context.getSharedPreferences("petcare_session", Context.MODE_PRIVATE)

    fun saveRole(role: UserRole) {
        preferences.edit()
            .putString(KEY_ROLE, role.name)
            .apply()
    }

    fun getRole(): UserRole {
        val value = preferences.getString(KEY_ROLE, UserRole.USER.name)
        return runCatching { UserRole.valueOf(value ?: UserRole.USER.name) }.getOrDefault(UserRole.USER)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val KEY_ROLE = "role"
    }
}
