package com.caetano.bruno.cartrawler

import android.app.Application
import com.caetano.bruno.cartrawler.di.appModules
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin

class CartrawlerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (canInitApp()) {
            installLeakCanary()
            initStetho()
            initKoin()
        }
    }

    private fun installLeakCanary() {
        LeakCanary.install(this)
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initKoin() {
        startKoin(this, appModules)
    }

    // if LeakCanary.isInAnalyzerProcess(this) is true, then
    // this process is dedicated to LeakCanary for heap analysis.
    // We should not init the app in this process.
    private fun canInitApp() = !LeakCanary.isInAnalyzerProcess(this)

}