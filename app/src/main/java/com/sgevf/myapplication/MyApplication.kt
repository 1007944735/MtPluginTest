package com.sgevf.myapplication

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class MyApplication: Application() {
    private val TAG = "MyApplication1111"

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                Log.d(TAG, "onActivityCreated: ${p0.localClassName}")
            }

            override fun onActivityStarted(p0: Activity) {
                Log.d(TAG, "onActivityStarted: ${p0.localClassName}")
            }

            override fun onActivityResumed(p0: Activity) {
                Log.d(TAG, "onActivityResumed: ${p0.localClassName}")
            }

            override fun onActivityPaused(p0: Activity) {
                Log.d(TAG, "onActivityPaused: ${p0.localClassName}")
            }

            override fun onActivityStopped(p0: Activity) {
                Log.d(TAG, "onActivityStopped: ${p0.localClassName}")
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                Log.d(TAG, "onActivitySaveInstanceState: ${p0.localClassName}")
            }

            override fun onActivityDestroyed(p0: Activity) {
                Log.d(TAG, "onActivityDestroyed: ${p0.localClassName}")
            }

        })
    }
}