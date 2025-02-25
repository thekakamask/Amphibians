package com.dcac.amphibians

import android.app.Application
import com.dcac.amphibians.data.AppContainer
import com.dcac.amphibians.data.DefaultAppContainer

// INITIALISE THE APPCONTAINER FOR DEPENDENCY INJECTION
class AmphibiansApplication : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}