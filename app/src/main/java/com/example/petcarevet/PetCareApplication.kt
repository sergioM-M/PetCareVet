package com.example.petcarevet

import android.app.Application
import com.example.petcarevet.di.ServiceLocator

class PetCareApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ServiceLocator.init(this)
    }
}
