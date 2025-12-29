package com.rebelclub.ircontrol2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IRControlApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}